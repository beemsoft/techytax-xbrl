/**
 * Copyright 2013 Hans Beemsterboer
 * 
 * This file is part of the TechyTax program.
 *
 * TechyTax is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * TechyTax is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TechyTax; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.techytax.business.zk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.techytax.domain.Customer;
import org.techytax.props.PropsFactory;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONObject;
import org.zkoss.json.parser.JSONParser;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

public class EditCustomerVM {
	@Wire("#resultWin")
	private Window win;

	private Customer customer;

	private String postalCodeCheckerKey;

	private String postalCodeCheckerSecret;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("customer") Customer customer) throws IOException {
		Selectors.wireComponents(view, this, false);
		this.customer = customer;
		if (customer == null) {
			Executions.sendRedirect("login.zul");
		}
		Properties props = PropsFactory.loadProperties();
		postalCodeCheckerKey = props.getProperty("postalcode.checker.key");
		postalCodeCheckerSecret = props.getProperty("postalcode.checker.secret");
	}

	@SuppressWarnings({ "rawtypes" })
	@NotifyChange("customer")
	@Command
	public void checkKvK() throws Exception {
		String postalCode = customer.getPostalCode();
		String kvkUrl = "http://api.openkvk.nl/json/SELECT%20*%20FROM%20kvk%20WHERE%20postcode%20=%20'" + postalCode + "';";
		URL obj = new URL(kvkUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		JSONParser parser = new JSONParser();
		JSONArray map = (JSONArray) parser.parse(in);
		Map map2 = (HashMap) map.get(0);
		Map map3 = (HashMap) map2.get("RESULT");
		JSONArray array = (JSONArray) map3.get("ROWS");
		if (array != null && array.size() > 0) {
			List<Customer> customers = getCustomers(array);
			Map<String, Object> arguments = new HashMap<>();
			arguments.put("customers", customers);
			String template = "select-customer.zul";
			Window window = (Window) Executions.createComponents(template, null, arguments);
			window.doModal();
		} else {
		}
	}

	@NotifyChange("customer")
	@Command
	public void checkPostalCode() throws Exception {
		if (customer.getPostalCode() == null || customer.getNumber() == null) {
			return;
		}
		String postalCodeCheckUrl = "https://api.postcode.nl/rest/addresses/" + customer.getPostalCode() + "/" + customer.getNumber();
		if (customer.getNumberExtension() != null) {
			postalCodeCheckUrl += "/" + customer.getNumberExtension();
		}
		URL url = new URL(postalCodeCheckUrl);
		URLConnection con = url.openConnection();

		String userpass = postalCodeCheckerKey + ":" + postalCodeCheckerSecret;
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());

		con.setRequestProperty("Authorization", basicAuth);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		JSONParser parser = new JSONParser();
		JSONObject map = (JSONObject) parser.parse(in);
		customer.setAddress((String) map.get("street"));
		customer.setCity((String) map.get("city"));

	}

	private List<Customer> getCustomers(JSONArray rowsArray) {
		List<Customer> customers = new ArrayList<>();
		Iterator<Object> iterator = rowsArray.iterator();
		while (iterator.hasNext()) {
			JSONArray customerArray = (JSONArray) iterator.next();
			String status = (String) customerArray.get(8);
			if (status == null) {
				Customer customer = new Customer();
				customer.setName((String) customerArray.get(1));
				customer.setCommerceNr(new BigInteger((String) customerArray.get(2)));
				customer.setAddress((String) customerArray.get(4));
				customer.setCity((String) customerArray.get(6));
				customer.setWebsite((String) customerArray.get(9));
				customers.add(customer);
			}
		}
		return customers;
	}

	@GlobalCommand
	@NotifyChange("customer")
	public void updateCustomerWithInfo(@BindingParam("selectedCustomer") Customer selectedCustomer) throws Exception {
		customer.setName(selectedCustomer.getName());
		customer.setCommerceNr(selectedCustomer.getCommerceNr());
		customer.setAddress(selectedCustomer.getAddress());
		customer.setCity(selectedCustomer.getCity());
		customer.setWebsite(selectedCustomer.getWebsite());
		customer.setNumber(null);
		customer.setNumberExtension(null);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void save() {
		Map args = new HashMap();
		args.put("customer", this.customer);
		BindUtils.postGlobalCommand("queueName", null, "refreshvalues", args);
		win.detach();
	}

	@Command
	public void cancel() {
		win.detach();
	}

	@Command
	public void closeThis() {
		win.detach();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
