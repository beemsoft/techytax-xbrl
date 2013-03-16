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
package org.techytax.digipoort;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;

import org.techytax.domain.VatDeclarationData;
import org.techytax.ws.AanleverResponse;
import org.techytax.ws.AanleverServiceFault;
import org.techytax.ws.GetBerichtsoortenResponse;
import org.techytax.ws.GetNieuweStatussenProcesResponse;
import org.techytax.ws.GetNieuweStatussenResponse;
import org.techytax.ws.GetProcessenResponse;
import org.techytax.ws.GetStatussenProcesResponse;
import org.techytax.wus.status.StatusinformatieServiceFault;

/**
 * Voor het (geautomatiseerd, bijv. via regelmatige „polling‟) ophalen van
 * statussen heeft de „getStatussenProces‟-request de voorkeur. Aan het verzoek
 * dient de periode, waarover statussen worden opgevraagd, te worden meegeven om
 * te voorkomen dat veel te veel resultaten worden teruggegeven. Het
 * „tijdstempelVanaf‟ is dan bijvoorbeeld afgeleid van het „tijdstempelTot‟ uit
 * de voorgaande request. Voordeel van deze methode is dat alle statussen uit
 * die periode worden teruggegeven. Bij gebruik van de
 * „getNieuweStatussenProces‟-request worden slechts statussen teruggegeven die
 * niet als reeds opgevraagd zijn gemarkeerd. Hierbij bestaat de mogelijkheid
 * dat deze statussen weliswaar zijn opgevraagd, maar niet daadwerkelijk zijn
 * gezien. De opvrager loopt daarmee het risico dergelijke statussen überhaupt
 * niet meer te zien te krijgen (tenzij alsnog de „getStatussenProces‟-request
 * wordt uitgevoerd).
 * 
 * @author hans
 * 
 */
public interface DigipoortService {

	/**
	 * De Aanleverservice stelt vast of een „aanleververzoek‟ van een
	 * aanleverende partij voldoet aan de koppelvlakspecificatie „WUS 2.0 voor
	 * Bedrijven‟. Indien het aanleververzoek voldoet aan de specificaties, dan
	 * start de Aanleverservice een nieuw verwerkingsproces met een uniek
	 * kenmerk (kenmerk). De Aanleverservice geeft in een synchroon proces
	 * antwoord op deze aanlevering. Dit antwoord bestaat uit de melding dat de
	 * aanlevering is gelukt en het kenmerk van deze aanlevering (SOAP response)
	 * of uit de melding dat de aanlevering is mislukt (SOAP fault). Wanneer de
	 * aanlevering succesvol is, stuurt de Aanleverservice het betreffende
	 * aanleverbericht naar het onderliggende verwerkingsproces.
	 * @param vatDeclarationData
	 * 
	 * @return
	 * @throws GeneralSecurityException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws AanleverServiceFault 
	 */
	public AanleverResponse aanleveren(VatDeclarationData vatDeclarationData) throws FileNotFoundException, IOException, GeneralSecurityException, AanleverServiceFault;

	/**
	 * Geeft alle statussen voor de belanghebbende die nog niet eerder bij dit
	 * certificaat opgehaald zijn (alle statussen waarmee voor de betreffende
	 * identiteitBelanghebbende en het meegegeven certificaat nog geen relatie
	 * is vastgelegd).
	 * @param vatDeclarationData
	 * 
	 * @return
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	public GetNieuweStatussenResponse getNieuweStatussen(
			VatDeclarationData vatDeclarationData) throws IOException, GeneralSecurityException;

	/**
	 * Geeft van een bepaald verwerkingsproces alle statussen terug die nog niet
	 * eerder zijn opgevraagd. Het criterium hierbij is: de statussen zijn niet
	 * eerder opgevraagd middels een verzoek waarbij een specifiek certificaat
	 * is gebruikt (alle statussen worden teruggegeven waarbij voor het
	 * betreffende kenmerk en het meegegeven certificaat nog geen relatie is
	 * vastgelegd). Er kan desgewenst ook een tijdsperiode worden meegegeven.
	 * @param vatDeclarationData
	 * @param kenmerk
	 * 
	 * @return
	 * @throws GeneralSecurityException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public GetNieuweStatussenProcesResponse getNieuweStatussenProces(
			VatDeclarationData vatDeclarationData, String kenmerk) throws FileNotFoundException, IOException, GeneralSecurityException;

	/**
	 * Geeft alle processen voor een bepaalde belanghebbende met een specifieke
	 * berichtsoort.
	 * @param vatDeclarationData
	 * 
	 * @return
	 * @throws GeneralSecurityException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws StatusinformatieServiceFault 
	 */
	public GetProcessenResponse getProcessen(
			VatDeclarationData vatDeclarationData) throws FileNotFoundException, IOException, GeneralSecurityException, StatusinformatieServiceFault;

	/**
	 * Geeft alle statussen die bij een bepaald verwerkingsproces horen. Er kan
	 * een tijdsperiode worden opgegeven.
	 * @param vatDeclarationData
	 * @param kenmerk
	 * 
	 * @return
	 * @throws GeneralSecurityException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public GetStatussenProcesResponse getStatussenProces(
			VatDeclarationData vatDeclarationData, String kenmerk) throws FileNotFoundException, IOException, GeneralSecurityException;

	/**
	 * Geeft alle berichtsoorten waarvoor namens een bepaalde belanghebbende
	 * informatie is aangeleverd.
	 * @param vatDeclarationData
	 * 
	 * @return
	 * @throws GeneralSecurityException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public GetBerichtsoortenResponse getBerichtsoorten(
			VatDeclarationData vatDeclarationData) throws FileNotFoundException, IOException, GeneralSecurityException;
}
