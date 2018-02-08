package talkable.userRegistration.createAccountPage;

import abstractObjects.AbstractElementsContainer;
import talkable.IntegrationInstructionPage.IntegrationInstructionPage;
import talkable.userRegistration.chosePlatformPage.ChosePlatformPage;

public class CreateAccountPage extends AbstractElementsContainer{

    private ElmntEmailInput emailInput;
    private ElmntPasswordInput passwordInput;
    private ElmntConfirmPasswordInput confirmPasswordInput;
    private ElmntSiteNameInput siteNameInput;
    private ElmntSiteURLInput siteUrlInput;
    private ElmntCurrencySelect currencySelect;
    private ElmntCreateAccountButton createAccountButton;
    private ElmntGoBackButton goBackButton;


    public CreateAccountPage(){
        emailInput = new ElmntEmailInput();
        passwordInput = new ElmntPasswordInput();
        confirmPasswordInput = new ElmntConfirmPasswordInput();
        siteNameInput = new ElmntSiteNameInput();
        siteUrlInput = new ElmntSiteURLInput();
        currencySelect = new ElmntCurrencySelect();
        createAccountButton = new ElmntCreateAccountButton();
        goBackButton = new ElmntGoBackButton();

    }

    /*Populate and submit Create Account Form
    * */
    public IntegrationInstructionPage populateAndSubmitForm(String email, String password, String siteName, String siteUrl){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
        siteNameInput.sendKeys(siteName);
        siteUrlInput.sendKeys(siteUrl);
        createAccountButton.click();

        return new IntegrationInstructionPage();

    }

    /*Only populate Create Account Form
    * Form is NOT submitted
    * */
    public void populateForm(String email, String password,String passwordConfirmation, String siteName, String siteUrl, String currency){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(passwordConfirmation);
        siteNameInput.sendKeys(siteName);
        siteUrlInput.sendKeys(siteUrl);
        currencySelect.selectByVisibleText(currency);
    }

    public void clickCreateAccountButton(){
        createAccountButton.click();
    }

    public ChosePlatformPage setGoBack(){
        goBackButton.click();
        return new ChosePlatformPage();
    }

   /* Set of Methods to get validation error messages displayed after each field:
   * */
    public String getElmntEmailInputErrorMsg() {
        ElmntEmailInputErrorMsg elmntEmailInputErrorMsg = new ElmntEmailInputErrorMsg();
        return elmntEmailInputErrorMsg.getText();
    }

    public String getPasswordInputErrorMsg() {
        ElmntPasswordInputErrorMsg passwordInputErrorMsg = new ElmntPasswordInputErrorMsg();
        return passwordInputErrorMsg.getText();
    }

    public String getConfirmPasswordInputErrorMsg() {
        ElmntConfirmPasswordInputErrorMsg confirmPasswordInputErrorMsg = new ElmntConfirmPasswordInputErrorMsg();
        return confirmPasswordInputErrorMsg.getText();
    }

    public String getSiteNameInputErrorMsg() {
        ElmntSiteNameInputErrorMsg siteNameInputErrorMsg = new ElmntSiteNameInputErrorMsg();
        return siteNameInputErrorMsg.getText();
    }

    public String getSiteUrlInputErrorMsg() {
        ElmntSiteUrlInputErrorMsg siteUrlInputErrorMsg = new ElmntSiteUrlInputErrorMsg();
        return siteUrlInputErrorMsg.getText();
    }
}
