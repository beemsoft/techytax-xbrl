<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<h4 class="section">User Guide</h4>
<div style="width:400">
<p>
TechyTax has the following Use Case scenario's.
</p>
<ul>
<li><a href="#add.cost">Add cost</a></li>
<li><a href="#display.cost.overview"">Display cost overview</a>
	<ul>
	<li><a href="#vat.return">VAT return</a></li>
	<li><a href="#vat.return.email">E-mail VAT return</a></li>
	<li><a href="#audit">Audit</a></li>
	<li><a href="#audit.email">E-mail Audit file</a></li>			
	</ul>
</li>
<li><a href="#load.data">Load data</a>
	<ul>
	<li><a href="#match.cost.types">Match cost types</a></li>
	</ul>
</li>
<li><a href="#fiscal.overview">Fiscal overview</a>
	<ul>
	<li><a href="#fiscal.overview.other">Fiscal overview for other years</a></li>
	</ul>
</li>
<li><a href="#add.account">Add account</a></li>
<li><a href="#depreciate.investments">Depreciate investments</a>
	<ul>
	<li><a href="#depreciation.correction">Depreciation correction</a></li>
	</ul>
</li>
<li><a href="#add.activa">Add activa</a></li>
<li><a href="#change.database">Change database</a></li>
<li><a href="#change.language">Change language</a></li>
</ul>
<h4 id="add.cost">Use Case: Add cost</h4>
<p>
The bulk of your costs will be loaded by the 'Load Data' Use Case. In order to add a cost manually:
</p>
<ul>
<li>Go to 'Add cost'.</li>
<li>Enter the details and select a cost type.</li>
<li>Click on 'Add'.</li>
</ul>
<h4 id="display.cost.overview">Use Case: Display cost overview</h4>
<ul>
<li>Go to 'Cost overview'.</li>
<li>The latest 10 costs are displayed.</li>
<li>Click on 'Show overview'.</li>
<li>All costs from the previous year are displayed.</li>
</ul>
<h5 id="vat.return">VAT return</h5>
<ul>
<li>Select the period for which you want to calculate the VAT return.</li>
<li>Select the option 'VAT'.</li>
<li>Click on 'Show overview'.</li>
</ul>
<h5 id="vat.return.email">E-mail VAT return</h5>
<ul>
<li>Be logged on as an admin user.</li>
<li>Do Use Case 'VAT return'.</li>
<li>Click on 'Submit'.</li>
</ul>
<h5 id="audit">Audit</h5>
<ul>
<li>Select the period for which you want to do an audit.</li>
<li>Select the option 'Audit'.</li>
<li>Click on 'Show overview'.</li>
</ul>
<h5 id="audit.email">E-mail Audit file</h5>
<ul>
<li>Be logged on as an admin user.</li>
<li>Do Use Case 'Audit'.</li>
<li>Click on 'Submit'.</li>
</ul>
<h4 id="load.data">Use Case: Load data</h4>
<p>
<b>NB:</b>Online bank systems keep data available only for the past 16 months. Be sure
to load your data in time. For example: at the beginning of a new year, load data, both private and business,
 from the complete previous year. Keep the dowloaded data as a backup!
</p>
<ul>
<li>Go to 'Load data'.</li>
<li>Select 'Private' or 'Business'.</li>
<li>Browse to the data file.</li>
<li>Click 'Load data'.</li>
<li>An overview is displayed that lists the costs. For business costs, the matched costtypes are also displayed.</li>
<li>If the business costs are matched fine, then click 'Load data into the system'. Otherwise, go to
subscenario 'Match cost types'.</li>
</ul>
<h5 id="match.cost.types">Match cost types</h5>
<ul>
<li>Go to 'Cost types'.</li>
<li>Select a cost type which has 'Current account' checked.</li>
<li>Add a match string for this cost type.</li>
<li>Go to 'Load data' again.</li>
</ul>
<h4 id="fiscal.overview">Use Case: Fiscal overview</h4>
<ul>
<li>Go to 'Cost overview'.</li>
<li>Select a period.</li>
<li>Select 'Depreciations'.</li>
<li>Click on 'Process depreciations into Activa'.</li>
<li>Go to 'Fiscal overview'.</li>
<li>The fiscal overview of the previous year is displayed.</li>
</ul>
<h5 id="fiscal.overview.other">Fiscal overview for other years</h5>
<ul>
<li>Go to 'Cost overview'.</li>
<li>Select a period.</li>
<li>Select 'All costs'.</li>
<li>Click on 'Show overview'.</li>
<li>Go to 'Fiscal overview'.</li>
</ul>
<h4 id="add.account">Use Case: Add account</h4> 
<ul>
<li>Go to 'Accounts'.</li>
<li>Click on 'New account'.</li>
<li>Enter the details.</li>
<li>Click on 'Add'.</li>
</ul>
<h4 id="depreciate.investments">Use Case: Depreciate investments</h4>
<ul>
<li>Go to 'Cost overview'.</li>
<li>Select a period.</li>
<li>Click the option 'investments'.</li>
<li>Click on 'Show overview'.</li>
<li>This will show all costs that are higher than the investment minimum.</li>
<li>Click on one of the costs.</li>
<li>If this cost is indeed an investment, change the costtype accordingly.</li>
<li>A link will appear that enables you to depreciate the cost over a period of 5 years. VAT is excluded from this
calculation. The remaining value is 10%.</li>
</ul>
<h5 id="depreciation.correction">Depreciation correction</h5>
<ul>
	<li>Go to 'Add cost'.</li>
	<li>Add a cost with cost type 'VAT correction of depreciation' and dated at the 31st of December.</li> 
</ul>
<h4 id="add.activa">Use Case: Add activa</h4>
<ul>
<li>Go to 'Cost overview'.</li>
<li>Select a period.</li>
<li>Click the option 'depreciations'.</li>
<li>Click on 'Show overview'.</li>
<li>A link will appear that enables you to process the depreciations in the activa list.</li>
</ul>
<h4 id="change.database">Use Case: Change database connection</h4>
<ul>
<li>Be logged on as an admin user.</li>
<li>Go to the 'Welcome' page.</li>
<li>Click on 'Database information'.</li>
<li>Change the database properties.</li>
<li>Click on 'Submit'.</li>
<li><b>Important: </b>This will change the database connection for <b>all users</b>.
</ul>
<h4 id="change.language">Use Case: Change language</h4>
<ul>
<li>Change the preferred language of your browser.</li>
<li>If you are logged on, log off.</li>
</ul>
</div>