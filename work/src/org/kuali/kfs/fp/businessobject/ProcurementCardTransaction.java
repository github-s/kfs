/*
 * Copyright (c) 2004, 2005 The National Association of College and University 
 * Business Officers, Cornell University, Trustees of Indiana University, 
 * Michigan State University Board of Trustees, Trustees of San Joaquin Delta 
 * College, University of Hawai'i, The Arizona Board of Regents on behalf of the 
 * University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); 
 * By obtaining, using and/or copying this Original Work, you agree that you 
 * have read, understand, and will comply with the terms and conditions of the 
 * Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,  DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN 
 * THE SOFTWARE.
 */

package org.kuali.module.financial.bo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.bo.BusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.ProjectCode;
import org.kuali.module.chart.bo.SubAccount;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProcurementCardTransaction extends BusinessObjectBase {

	private Integer transactionSequenceRowNumber;
	private String transactionCreditCardNumber;
	private KualiDecimal financialDocumentTotalAmount;
	private String transactionDebitCreditCode;
	private String chartOfAccountsCode;
	private String accountNumber;
	private String subAccountNumber;
	private String financialObjectCode;
	private String financialSubObjectCode;
	private String projectCode;
	private Date transactionCycleStartDate;
	private Date transactionCycleEndDate;
	private String cardHolderName;
	private Date transactionDate;
	private String transactionReferenceNumber;
	private String transactionMerchantCategoryCode;
	private Date transactionPostingDate;
	private String transactionOriginalCurrencyCode;
	private String transactionBillingCurrencyCode;
	private KualiDecimal transactionOriginalCurrencyAmount;
	private BigDecimal transactionCurrencyExchangeRate;
	private KualiDecimal transactionSettlementAmount;
	private KualiDecimal transactionSalesTaxAmount;
	private boolean transactionTaxExemptIndicator;
	private boolean transactionPurchaseIdentifierIndicator;
	private String transactionPurchaseIdentifierDescription;
	private String transactionUnitContactName;
	private String transactionTravelAuthorizationCode;
	private String transactionPointOfSaleCode;
	private String vendorName;
	private String vendorLine1Address;
	private String vendorLine2Address;
	private String vendorCityName;
	private String vendorStateCode;
	private String vendorZipCode;
	private String vendorOrderNumber;
	private String visaVendorIdentifier;
	private String cardHolderAlternateName;
	private String cardHolderLine1Address;
	private String cardHolderLine2Address;
	private String cardHolderCityName;
	private String cardHolderStateCode;
	private String cardHolderZipCode;
	private String cardHolderWorkPhoneNumber;
	private KualiDecimal cardLimit;
	private KualiDecimal cardCycleAmountLimit;
	private KualiDecimal cardCycleVolumeLimit;
	private String cardStatusCode;
	private String cardNoteText;

    private Chart chartOfAccounts;
	private Account account;
    private ProjectCode project;
    private SubAccount subAccount;
    
	/**
	 * Default constructor.
	 */
	public ProcurementCardTransaction() {

	}

	/**
	 * Gets the transactionSequenceRowNumber attribute.
	 * 
	 * @return - Returns the transactionSequenceRowNumber
	 * 
	 */
	public Integer getTransactionSequenceRowNumber() { 
		return transactionSequenceRowNumber;
	}

	/**
	 * Sets the transactionSequenceRowNumber attribute.
	 * 
	 * @param - transactionSequenceRowNumber The transactionSequenceRowNumber to set.
	 * 
	 */
	public void setTransactionSequenceRowNumber(Integer transactionSequenceRowNumber) {
		this.transactionSequenceRowNumber = transactionSequenceRowNumber;
	}


	/**
	 * Gets the transactionCreditCardNumber attribute.
	 * 
	 * @return - Returns the transactionCreditCardNumber
	 * 
	 */
	public String getTransactionCreditCardNumber() { 
		return transactionCreditCardNumber;
	}

	/**
	 * Sets the transactionCreditCardNumber attribute.
	 * 
	 * @param - transactionCreditCardNumber The transactionCreditCardNumber to set.
	 * 
	 */
	public void setTransactionCreditCardNumber(String transactionCreditCardNumber) {
		this.transactionCreditCardNumber = transactionCreditCardNumber;
	}


	/**
	 * Gets the financialDocumentTotalAmount attribute.
	 * 
	 * @return - Returns the financialDocumentTotalAmount
	 * 
	 */
	public KualiDecimal getFinancialDocumentTotalAmount() { 
		return financialDocumentTotalAmount;
	}

	/**
	 * Sets the financialDocumentTotalAmount attribute.
	 * 
	 * @param - financialDocumentTotalAmount The financialDocumentTotalAmount to set.
	 * 
	 */
	public void setFinancialDocumentTotalAmount(KualiDecimal financialDocumentTotalAmount) {
		this.financialDocumentTotalAmount = financialDocumentTotalAmount;
	}


	/**
	 * Gets the transactionDebitCreditCode attribute.
	 * 
	 * @return - Returns the transactionDebitCreditCode
	 * 
	 */
	public String getTransactionDebitCreditCode() { 
		return transactionDebitCreditCode;
	}

	/**
	 * Sets the transactionDebitCreditCode attribute.
	 * 
	 * @param - transactionDebitCreditCode The transactionDebitCreditCode to set.
	 * 
	 */
	public void setTransactionDebitCreditCode(String transactionDebitCreditCode) {
		this.transactionDebitCreditCode = transactionDebitCreditCode;
	}


	/**
	 * Gets the chartOfAccountsCode attribute.
	 * 
	 * @return - Returns the chartOfAccountsCode
	 * 
	 */
	public String getChartOfAccountsCode() { 
		return chartOfAccountsCode;
	}

	/**
	 * Sets the chartOfAccountsCode attribute.
	 * 
	 * @param - chartOfAccountsCode The chartOfAccountsCode to set.
	 * 
	 */
	public void setChartOfAccountsCode(String chartOfAccountsCode) {
		this.chartOfAccountsCode = chartOfAccountsCode;
	}


	/**
	 * Gets the accountNumber attribute.
	 * 
	 * @return - Returns the accountNumber
	 * 
	 */
	public String getAccountNumber() { 
		return accountNumber;
	}

	/**
	 * Sets the accountNumber attribute.
	 * 
	 * @param - accountNumber The accountNumber to set.
	 * 
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	/**
	 * Gets the subAccountNumber attribute.
	 * 
	 * @return - Returns the subAccountNumber
	 * 
	 */
	public String getSubAccountNumber() { 
		return subAccountNumber;
	}

	/**
	 * Sets the subAccountNumber attribute.
	 * 
	 * @param - subAccountNumber The subAccountNumber to set.
	 * 
	 */
	public void setSubAccountNumber(String subAccountNumber) {
		this.subAccountNumber = subAccountNumber;
	}


	/**
	 * Gets the financialObjectCode attribute.
	 * 
	 * @return - Returns the financialObjectCode
	 * 
	 */
	public String getFinancialObjectCode() { 
		return financialObjectCode;
	}

	/**
	 * Sets the financialObjectCode attribute.
	 * 
	 * @param - financialObjectCode The financialObjectCode to set.
	 * 
	 */
	public void setFinancialObjectCode(String financialObjectCode) {
		this.financialObjectCode = financialObjectCode;
	}


	/**
	 * Gets the financialSubObjectCode attribute.
	 * 
	 * @return - Returns the financialSubObjectCode
	 * 
	 */
	public String getFinancialSubObjectCode() { 
		return financialSubObjectCode;
	}

	/**
	 * Sets the financialSubObjectCode attribute.
	 * 
	 * @param - financialSubObjectCode The financialSubObjectCode to set.
	 * 
	 */
	public void setFinancialSubObjectCode(String financialSubObjectCode) {
		this.financialSubObjectCode = financialSubObjectCode;
	}


	/**
	 * Gets the projectCode attribute.
	 * 
	 * @return - Returns the projectCode
	 * 
	 */
	public String getProjectCode() { 
		return projectCode;
	}

	/**
	 * Sets the projectCode attribute.
	 * 
	 * @param - projectCode The projectCode to set.
	 * 
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}


	/**
	 * Gets the transactionCycleStartDate attribute.
	 * 
	 * @return - Returns the transactionCycleStartDate
	 * 
	 */
	public Date getTransactionCycleStartDate() { 
		return transactionCycleStartDate;
	}

	/**
	 * Sets the transactionCycleStartDate attribute.
	 * 
	 * @param - transactionCycleStartDate The transactionCycleStartDate to set.
	 * 
	 */
	public void setTransactionCycleStartDate(Date transactionCycleStartDate) {
		this.transactionCycleStartDate = transactionCycleStartDate;
	}


	/**
	 * Gets the transactionCycleEndDate attribute.
	 * 
	 * @return - Returns the transactionCycleEndDate
	 * 
	 */
	public Date getTransactionCycleEndDate() { 
		return transactionCycleEndDate;
	}

	/**
	 * Sets the transactionCycleEndDate attribute.
	 * 
	 * @param - transactionCycleEndDate The transactionCycleEndDate to set.
	 * 
	 */
	public void setTransactionCycleEndDate(Date transactionCycleEndDate) {
		this.transactionCycleEndDate = transactionCycleEndDate;
	}


	/**
	 * Gets the cardHolderName attribute.
	 * 
	 * @return - Returns the cardHolderName
	 * 
	 */
	public String getCardHolderName() { 
		return cardHolderName;
	}

	/**
	 * Sets the cardHolderName attribute.
	 * 
	 * @param - cardHolderName The cardHolderName to set.
	 * 
	 */
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}


	/**
	 * Gets the transactionDate attribute.
	 * 
	 * @return - Returns the transactionDate
	 * 
	 */
	public Date getTransactionDate() { 
		return transactionDate;
	}

	/**
	 * Sets the transactionDate attribute.
	 * 
	 * @param - transactionDate The transactionDate to set.
	 * 
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}


	/**
	 * Gets the transactionReferenceNumber attribute.
	 * 
	 * @return - Returns the transactionReferenceNumber
	 * 
	 */
	public String getTransactionReferenceNumber() { 
		return transactionReferenceNumber;
	}

	/**
	 * Sets the transactionReferenceNumber attribute.
	 * 
	 * @param - transactionReferenceNumber The transactionReferenceNumber to set.
	 * 
	 */
	public void setTransactionReferenceNumber(String transactionReferenceNumber) {
		this.transactionReferenceNumber = transactionReferenceNumber;
	}


	/**
	 * Gets the transactionMerchantCategoryCode attribute.
	 * 
	 * @return - Returns the transactionMerchantCategoryCode
	 * 
	 */
	public String getTransactionMerchantCategoryCode() { 
		return transactionMerchantCategoryCode;
	}

	/**
	 * Sets the transactionMerchantCategoryCode attribute.
	 * 
	 * @param - transactionMerchantCategoryCode The transactionMerchantCategoryCode to set.
	 * 
	 */
	public void setTransactionMerchantCategoryCode(String transactionMerchantCategoryCode) {
		this.transactionMerchantCategoryCode = transactionMerchantCategoryCode;
	}


	/**
	 * Gets the transactionPostingDate attribute.
	 * 
	 * @return - Returns the transactionPostingDate
	 * 
	 */
	public Date getTransactionPostingDate() { 
		return transactionPostingDate;
	}

	/**
	 * Sets the transactionPostingDate attribute.
	 * 
	 * @param - transactionPostingDate The transactionPostingDate to set.
	 * 
	 */
	public void setTransactionPostingDate(Date transactionPostingDate) {
		this.transactionPostingDate = transactionPostingDate;
	}


	/**
	 * Gets the transactionOriginalCurrencyCode attribute.
	 * 
	 * @return - Returns the transactionOriginalCurrencyCode
	 * 
	 */
	public String getTransactionOriginalCurrencyCode() { 
		return transactionOriginalCurrencyCode;
	}

	/**
	 * Sets the transactionOriginalCurrencyCode attribute.
	 * 
	 * @param - transactionOriginalCurrencyCode The transactionOriginalCurrencyCode to set.
	 * 
	 */
	public void setTransactionOriginalCurrencyCode(String transactionOriginalCurrencyCode) {
		this.transactionOriginalCurrencyCode = transactionOriginalCurrencyCode;
	}


	/**
	 * Gets the transactionBillingCurrencyCode attribute.
	 * 
	 * @return - Returns the transactionBillingCurrencyCode
	 * 
	 */
	public String getTransactionBillingCurrencyCode() { 
		return transactionBillingCurrencyCode;
	}

	/**
	 * Sets the transactionBillingCurrencyCode attribute.
	 * 
	 * @param - transactionBillingCurrencyCode The transactionBillingCurrencyCode to set.
	 * 
	 */
	public void setTransactionBillingCurrencyCode(String transactionBillingCurrencyCode) {
		this.transactionBillingCurrencyCode = transactionBillingCurrencyCode;
	}


	/**
	 * Gets the transactionOriginalCurrencyAmount attribute.
	 * 
	 * @return - Returns the transactionOriginalCurrencyAmount
	 * 
	 */
	public KualiDecimal getTransactionOriginalCurrencyAmount() { 
		return transactionOriginalCurrencyAmount;
	}

	/**
	 * Sets the transactionOriginalCurrencyAmount attribute.
	 * 
	 * @param - transactionOriginalCurrencyAmount The transactionOriginalCurrencyAmount to set.
	 * 
	 */
	public void setTransactionOriginalCurrencyAmount(KualiDecimal transactionOriginalCurrencyAmount) {
		this.transactionOriginalCurrencyAmount = transactionOriginalCurrencyAmount;
	}


	/**
	 * Gets the transactionCurrencyExchangeRate attribute.
	 * 
	 * @return - Returns the transactionCurrencyExchangeRate
	 * 
	 */
	public BigDecimal getTransactionCurrencyExchangeRate() { 
		return transactionCurrencyExchangeRate;
	}

	/**
	 * Sets the transactionCurrencyExchangeRate attribute.
	 * 
	 * @param - transactionCurrencyExchangeRate The transactionCurrencyExchangeRate to set.
	 * 
	 */
	public void setTransactionCurrencyExchangeRate(BigDecimal transactionCurrencyExchangeRate) {
		this.transactionCurrencyExchangeRate = transactionCurrencyExchangeRate;
	}


	/**
	 * Gets the transactionSettlementAmount attribute.
	 * 
	 * @return - Returns the transactionSettlementAmount
	 * 
	 */
	public KualiDecimal getTransactionSettlementAmount() { 
		return transactionSettlementAmount;
	}

	/**
	 * Sets the transactionSettlementAmount attribute.
	 * 
	 * @param - transactionSettlementAmount The transactionSettlementAmount to set.
	 * 
	 */
	public void setTransactionSettlementAmount(KualiDecimal transactionSettlementAmount) {
		this.transactionSettlementAmount = transactionSettlementAmount;
	}


	/**
	 * Gets the transactionSalesTaxAmount attribute.
	 * 
	 * @return - Returns the transactionSalesTaxAmount
	 * 
	 */
	public KualiDecimal getTransactionSalesTaxAmount() { 
		return transactionSalesTaxAmount;
	}

	/**
	 * Sets the transactionSalesTaxAmount attribute.
	 * 
	 * @param - transactionSalesTaxAmount The transactionSalesTaxAmount to set.
	 * 
	 */
	public void setTransactionSalesTaxAmount(KualiDecimal transactionSalesTaxAmount) {
		this.transactionSalesTaxAmount = transactionSalesTaxAmount;
	}


	/**
	 * Gets the transactionTaxExemptIndicator attribute.
	 * 
	 * @return - Returns the transactionTaxExemptIndicator
	 * 
	 */
	public boolean getTransactionTaxExemptIndicator() { 
		return transactionTaxExemptIndicator;
	}

	/**
	 * Sets the transactionTaxExemptIndicator attribute.
	 * 
	 * @param - transactionTaxExemptIndicator The transactionTaxExemptIndicator to set.
	 * 
	 */
	public void setTransactionTaxExemptIndicator(boolean transactionTaxExemptIndicator) {
		this.transactionTaxExemptIndicator = transactionTaxExemptIndicator;
	}


	/**
	 * Gets the transactionPurchaseIdentifierIndicator attribute.
	 * 
	 * @return - Returns the transactionPurchaseIdentifierIndicator
	 * 
	 */
	public boolean getTransactionPurchaseIdentifierIndicator() { 
		return transactionPurchaseIdentifierIndicator;
	}

	/**
	 * Sets the transactionPurchaseIdentifierIndicator attribute.
	 * 
	 * @param - transactionPurchaseIdentifierIndicator The transactionPurchaseIdentifierIndicator to set.
	 * 
	 */
	public void setTransactionPurchaseIdentifierIndicator(boolean transactionPurchaseIdentifierIndicator) {
		this.transactionPurchaseIdentifierIndicator = transactionPurchaseIdentifierIndicator;
	}


	/**
	 * Gets the transactionPurchaseIdentifierDescription attribute.
	 * 
	 * @return - Returns the transactionPurchaseIdentifierDescription
	 * 
	 */
	public String getTransactionPurchaseIdentifierDescription() { 
		return transactionPurchaseIdentifierDescription;
	}

	/**
	 * Sets the transactionPurchaseIdentifierDescription attribute.
	 * 
	 * @param - transactionPurchaseIdentifierDescription The transactionPurchaseIdentifierDescription to set.
	 * 
	 */
	public void setTransactionPurchaseIdentifierDescription(String transactionPurchaseIdentifierDescription) {
		this.transactionPurchaseIdentifierDescription = transactionPurchaseIdentifierDescription;
	}


	/**
	 * Gets the transactionUnitContactName attribute.
	 * 
	 * @return - Returns the transactionUnitContactName
	 * 
	 */
	public String getTransactionUnitContactName() { 
		return transactionUnitContactName;
	}

	/**
	 * Sets the transactionUnitContactName attribute.
	 * 
	 * @param - transactionUnitContactName The transactionUnitContactName to set.
	 * 
	 */
	public void setTransactionUnitContactName(String transactionUnitContactName) {
		this.transactionUnitContactName = transactionUnitContactName;
	}


	/**
	 * Gets the transactionTravelAuthorizationCode attribute.
	 * 
	 * @return - Returns the transactionTravelAuthorizationCode
	 * 
	 */
	public String getTransactionTravelAuthorizationCode() { 
		return transactionTravelAuthorizationCode;
	}

	/**
	 * Sets the transactionTravelAuthorizationCode attribute.
	 * 
	 * @param - transactionTravelAuthorizationCode The transactionTravelAuthorizationCode to set.
	 * 
	 */
	public void setTransactionTravelAuthorizationCode(String transactionTravelAuthorizationCode) {
		this.transactionTravelAuthorizationCode = transactionTravelAuthorizationCode;
	}


	/**
	 * Gets the transactionPointOfSaleCode attribute.
	 * 
	 * @return - Returns the transactionPointOfSaleCode
	 * 
	 */
	public String getTransactionPointOfSaleCode() { 
		return transactionPointOfSaleCode;
	}

	/**
	 * Sets the transactionPointOfSaleCode attribute.
	 * 
	 * @param - transactionPointOfSaleCode The transactionPointOfSaleCode to set.
	 * 
	 */
	public void setTransactionPointOfSaleCode(String transactionPointOfSaleCode) {
		this.transactionPointOfSaleCode = transactionPointOfSaleCode;
	}


	/**
	 * Gets the vendorName attribute.
	 * 
	 * @return - Returns the vendorName
	 * 
	 */
	public String getVendorName() { 
		return vendorName;
	}

	/**
	 * Sets the vendorName attribute.
	 * 
	 * @param - vendorName The vendorName to set.
	 * 
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}


	/**
	 * Gets the vendorLine1Address attribute.
	 * 
	 * @return - Returns the vendorLine1Address
	 * 
	 */
	public String getVendorLine1Address() { 
		return vendorLine1Address;
	}

	/**
	 * Sets the vendorLine1Address attribute.
	 * 
	 * @param - vendorLine1Address The vendorLine1Address to set.
	 * 
	 */
	public void setVendorLine1Address(String vendorLine1Address) {
		this.vendorLine1Address = vendorLine1Address;
	}


	/**
	 * Gets the vendorLine2Address attribute.
	 * 
	 * @return - Returns the vendorLine2Address
	 * 
	 */
	public String getVendorLine2Address() { 
		return vendorLine2Address;
	}

	/**
	 * Sets the vendorLine2Address attribute.
	 * 
	 * @param - vendorLine2Address The vendorLine2Address to set.
	 * 
	 */
	public void setVendorLine2Address(String vendorLine2Address) {
		this.vendorLine2Address = vendorLine2Address;
	}


	/**
	 * Gets the vendorCityName attribute.
	 * 
	 * @return - Returns the vendorCityName
	 * 
	 */
	public String getVendorCityName() { 
		return vendorCityName;
	}

	/**
	 * Sets the vendorCityName attribute.
	 * 
	 * @param - vendorCityName The vendorCityName to set.
	 * 
	 */
	public void setVendorCityName(String vendorCityName) {
		this.vendorCityName = vendorCityName;
	}


	/**
	 * Gets the vendorStateCode attribute.
	 * 
	 * @return - Returns the vendorStateCode
	 * 
	 */
	public String getVendorStateCode() { 
		return vendorStateCode;
	}

	/**
	 * Sets the vendorStateCode attribute.
	 * 
	 * @param - vendorStateCode The vendorStateCode to set.
	 * 
	 */
	public void setVendorStateCode(String vendorStateCode) {
		this.vendorStateCode = vendorStateCode;
	}


	/**
	 * Gets the vendorZipCode attribute.
	 * 
	 * @return - Returns the vendorZipCode
	 * 
	 */
	public String getVendorZipCode() { 
		return vendorZipCode;
	}

	/**
	 * Sets the vendorZipCode attribute.
	 * 
	 * @param - vendorZipCode The vendorZipCode to set.
	 * 
	 */
	public void setVendorZipCode(String vendorZipCode) {
		this.vendorZipCode = vendorZipCode;
	}


	/**
	 * Gets the vendorOrderNumber attribute.
	 * 
	 * @return - Returns the vendorOrderNumber
	 * 
	 */
	public String getVendorOrderNumber() { 
		return vendorOrderNumber;
	}

	/**
	 * Sets the vendorOrderNumber attribute.
	 * 
	 * @param - vendorOrderNumber The vendorOrderNumber to set.
	 * 
	 */
	public void setVendorOrderNumber(String vendorOrderNumber) {
		this.vendorOrderNumber = vendorOrderNumber;
	}


	/**
	 * Gets the visaVendorIdentifier attribute.
	 * 
	 * @return - Returns the visaVendorIdentifier
	 * 
	 */
	public String getVisaVendorIdentifier() { 
		return visaVendorIdentifier;
	}

	/**
	 * Sets the visaVendorIdentifier attribute.
	 * 
	 * @param - visaVendorIdentifier The visaVendorIdentifier to set.
	 * 
	 */
	public void setVisaVendorIdentifier(String visaVendorIdentifier) {
		this.visaVendorIdentifier = visaVendorIdentifier;
	}


	/**
	 * Gets the cardHolderAlternateName attribute.
	 * 
	 * @return - Returns the cardHolderAlternateName
	 * 
	 */
	public String getCardHolderAlternateName() { 
		return cardHolderAlternateName;
	}

	/**
	 * Sets the cardHolderAlternateName attribute.
	 * 
	 * @param - cardHolderAlternateName The cardHolderAlternateName to set.
	 * 
	 */
	public void setCardHolderAlternateName(String cardHolderAlternateName) {
		this.cardHolderAlternateName = cardHolderAlternateName;
	}


	/**
	 * Gets the cardHolderLine1Address attribute.
	 * 
	 * @return - Returns the cardHolderLine1Address
	 * 
	 */
	public String getCardHolderLine1Address() { 
		return cardHolderLine1Address;
	}

	/**
	 * Sets the cardHolderLine1Address attribute.
	 * 
	 * @param - cardHolderLine1Address The cardHolderLine1Address to set.
	 * 
	 */
	public void setCardHolderLine1Address(String cardHolderLine1Address) {
		this.cardHolderLine1Address = cardHolderLine1Address;
	}


	/**
	 * Gets the cardHolderLine2Address attribute.
	 * 
	 * @return - Returns the cardHolderLine2Address
	 * 
	 */
	public String getCardHolderLine2Address() { 
		return cardHolderLine2Address;
	}

	/**
	 * Sets the cardHolderLine2Address attribute.
	 * 
	 * @param - cardHolderLine2Address The cardHolderLine2Address to set.
	 * 
	 */
	public void setCardHolderLine2Address(String cardHolderLine2Address) {
		this.cardHolderLine2Address = cardHolderLine2Address;
	}


	/**
	 * Gets the cardHolderCityName attribute.
	 * 
	 * @return - Returns the cardHolderCityName
	 * 
	 */
	public String getCardHolderCityName() { 
		return cardHolderCityName;
	}

	/**
	 * Sets the cardHolderCityName attribute.
	 * 
	 * @param - cardHolderCityName The cardHolderCityName to set.
	 * 
	 */
	public void setCardHolderCityName(String cardHolderCityName) {
		this.cardHolderCityName = cardHolderCityName;
	}


	/**
	 * Gets the cardHolderStateCode attribute.
	 * 
	 * @return - Returns the cardHolderStateCode
	 * 
	 */
	public String getCardHolderStateCode() { 
		return cardHolderStateCode;
	}

	/**
	 * Sets the cardHolderStateCode attribute.
	 * 
	 * @param - cardHolderStateCode The cardHolderStateCode to set.
	 * 
	 */
	public void setCardHolderStateCode(String cardHolderStateCode) {
		this.cardHolderStateCode = cardHolderStateCode;
	}


	/**
	 * Gets the cardHolderZipCode attribute.
	 * 
	 * @return - Returns the cardHolderZipCode
	 * 
	 */
	public String getCardHolderZipCode() { 
		return cardHolderZipCode;
	}

	/**
	 * Sets the cardHolderZipCode attribute.
	 * 
	 * @param - cardHolderZipCode The cardHolderZipCode to set.
	 * 
	 */
	public void setCardHolderZipCode(String cardHolderZipCode) {
		this.cardHolderZipCode = cardHolderZipCode;
	}


	/**
	 * Gets the cardHolderWorkPhoneNumber attribute.
	 * 
	 * @return - Returns the cardHolderWorkPhoneNumber
	 * 
	 */
	public String getCardHolderWorkPhoneNumber() { 
		return cardHolderWorkPhoneNumber;
	}

	/**
	 * Sets the cardHolderWorkPhoneNumber attribute.
	 * 
	 * @param - cardHolderWorkPhoneNumber The cardHolderWorkPhoneNumber to set.
	 * 
	 */
	public void setCardHolderWorkPhoneNumber(String cardHolderWorkPhoneNumber) {
		this.cardHolderWorkPhoneNumber = cardHolderWorkPhoneNumber;
	}


	/**
	 * Gets the cardLimit attribute.
	 * 
	 * @return - Returns the cardLimit
	 * 
	 */
	public KualiDecimal getCardLimit() { 
		return cardLimit;
	}

	/**
	 * Sets the cardLimit attribute.
	 * 
	 * @param - cardLimit The cardLimit to set.
	 * 
	 */
	public void setCardLimit(KualiDecimal cardLimit) {
		this.cardLimit = cardLimit;
	}


	/**
	 * Gets the cardCycleAmountLimit attribute.
	 * 
	 * @return - Returns the cardCycleAmountLimit
	 * 
	 */
	public KualiDecimal getCardCycleAmountLimit() { 
		return cardCycleAmountLimit;
	}

	/**
	 * Sets the cardCycleAmountLimit attribute.
	 * 
	 * @param - cardCycleAmountLimit The cardCycleAmountLimit to set.
	 * 
	 */
	public void setCardCycleAmountLimit(KualiDecimal cardCycleAmountLimit) {
		this.cardCycleAmountLimit = cardCycleAmountLimit;
	}


	/**
	 * Gets the cardCycleVolumeLimit attribute.
	 * 
	 * @return - Returns the cardCycleVolumeLimit
	 * 
	 */
	public KualiDecimal getCardCycleVolumeLimit() { 
		return cardCycleVolumeLimit;
	}

	/**
	 * Sets the cardCycleVolumeLimit attribute.
	 * 
	 * @param - cardCycleVolumeLimit The cardCycleVolumeLimit to set.
	 * 
	 */
	public void setCardCycleVolumeLimit(KualiDecimal cardCycleVolumeLimit) {
		this.cardCycleVolumeLimit = cardCycleVolumeLimit;
	}


	/**
	 * Gets the cardStatusCode attribute.
	 * 
	 * @return - Returns the cardStatusCode
	 * 
	 */
	public String getCardStatusCode() { 
		return cardStatusCode;
	}

	/**
	 * Sets the cardStatusCode attribute.
	 * 
	 * @param - cardStatusCode The cardStatusCode to set.
	 * 
	 */
	public void setCardStatusCode(String cardStatusCode) {
		this.cardStatusCode = cardStatusCode;
	}


	/**
	 * Gets the cardNoteText attribute.
	 * 
	 * @return - Returns the cardNoteText
	 * 
	 */
	public String getCardNoteText() { 
		return cardNoteText;
	}

	/**
	 * Sets the cardNoteText attribute.
	 * 
	 * @param - cardNoteText The cardNoteText to set.
	 * 
	 */
	public void setCardNoteText(String cardNoteText) {
		this.cardNoteText = cardNoteText;
	}


	/**
	 * Gets the chartOfAccounts attribute.
	 * 
	 * @return - Returns the chartOfAccounts
	 * 
	 */
	public Chart getChartOfAccounts() { 
		return chartOfAccounts;
	}

	/**
	 * Sets the chartOfAccounts attribute.
	 * 
	 * @param - chartOfAccounts The chartOfAccounts to set.
	 * @deprecated
	 */
	public void setChartOfAccounts(Chart chartOfAccounts) {
		this.chartOfAccounts = chartOfAccounts;
	}

	/**
	 * Gets the account attribute.
	 * 
	 * @return - Returns the account
	 * 
	 */
	public Account getAccount() { 
		return account;
	}

	/**
	 * Sets the account attribute.
	 * 
	 * @param - account The account to set.
	 * @deprecated
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

    /**
     * @return Returns the project.
     */
    public ProjectCode getProject() {
        return project;
    }

    /**
     * @param project The project to set.
     */
    public void setProject(ProjectCode project) {
        this.project = project;
    }

    /**
     * @return Returns the subAccount.
     */
    public SubAccount getSubAccount() {
        return subAccount;
    }

    /**
     * @param subAccount The subAccount to set.
     */
    public void setSubAccount(SubAccount subAccount) {
        this.subAccount = subAccount;
    }
    
    /**
     * @see org.kuali.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        if (this.transactionSequenceRowNumber != null) {
            m.put("transactionSequenceRowNumber", this.transactionSequenceRowNumber.toString());
        }
        return m;
    }
    
    
}
