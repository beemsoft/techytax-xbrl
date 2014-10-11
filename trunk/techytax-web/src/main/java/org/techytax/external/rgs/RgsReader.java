/**
 * Copyright 2014 Hans Beemsterboer
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
package org.techytax.external.rgs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.cxf.common.util.StringUtils;
import org.techytax.external.domain.ExternalCostType;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.jpa.entities.EntityManagerHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RgsReader {

	public static void main(String[] args) throws FileNotFoundException, SAXException, IOException, XPathExpressionException {

		GenericDao<ExternalCostType> genericDao = new GenericDao<>(EntityManagerHelper.getEntityManager(), ExternalCostType.class);

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();

			Document document = builder.parse(new FileInputStream("RGS1.0.xml"));

			XPath xPath = XPathFactory.newInstance().newXPath();

			String expression = "//RGS/Accounts/Account";

			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

			System.out.println(nodeList.getLength());

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				String code = xPath.compile("Referentiecode").evaluate(node);
				String description = xPath.compile("Description").evaluate(node);
				String level = xPath.compile("Level").evaluate(node);
				if (StringUtils.isEmpty(level)) {
					level = "4";
				}
				
				ExternalCostType externalCostType = new ExternalCostType();
				externalCostType.setCode(code);
				externalCostType.setDescription(description);
				externalCostType.setLevel(Integer.parseInt(level));
				genericDao.persistEntity(externalCostType);
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

}
