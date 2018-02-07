package CodeHouse;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class Page {

    protected static WebDriver driver;
    static Logger logger;


    /**
     *
     * @param driver configure the WebDriver
     * @param logger configure the Logger.
     */
    public Page(WebDriver driver,Logger logger){
        this.driver = driver;
        this.logger = logger;
    }

    /**
     *
     * @param driver Constructor to configure the WebDriver.
     */
    public Page(WebDriver driver){
        this.driver = driver;
    }

    /**
     *
     * @param message is the String message that needs to be passed.
     *                and it return the Java Logger Info method.
     *                Recommended to be replaced with System.out.print();
     *                Adds to logger. Check the overloaded version for other loggers like log4j.
     */
    protected void info(String message){
        logger.info(message);
    }

    /**
     *
     * @param message
     */
    protected void warn(String message){
        logger.warning(message);
    }


    /**
     *
     * @return the current initialised driver.
     */
    protected WebDriver getDriver(){
        return driver;
    }

    /**
     *
     * @return the object of Actions Class from Selenium.
     */
    protected Actions getActionsHandle(){
        Actions actions = new Actions(driver);
        return actions;
    }

    /**
     *
     * @param timeInSeconds is the time the WEbDriver waits
     * @return the object of WebDriverWait
     */
    protected WebDriverWait getWebDriverWait(int timeInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        return wait;
    }

    /**
     *
     * @param element is the element that needs the select operation to be performed.
     *                This is used for drop downs.
     *
     * @return the object of Class Select.
     */
    protected Select getSelectObject(WebElement element){
        Select select = new Select(element);
        return select;
    }

    /**
     *
     * @param selectElement  is the dropdown element to be used.
     * @param dropdownValue is the value to select the dropdown option by.
     */
    protected void selectByValue(WebElement selectElement, String dropdownValue){
        try {
            getSelectObject(selectElement).selectByValue(dropdownValue);
            info("Selected the element with value attribute : "+dropdownValue);
        }catch (NoSuchElementException nsee){
            info("\nNo Such Element Exception Occurred!. Please check the Element locator.");
            nsee.printStackTrace();
        }catch (StaleElementReferenceException sere){
            info("\nStale Element Reference Exception Occurred!. Please add some explicit wait.");
            sere.printStackTrace();
        }
    }

    /**
     *
     * @param selectElement  is the dropdown element to be used.
     *                       Deselects all the dropdown elements.
     */
    protected void deselectAll(WebElement selectElement){
        try {
            getSelectObject(selectElement).deselectAll();
            info("Deselected the element");
        }catch (NoSuchElementException nsee){
            info("\nNo Such Element Exception Occurred!. Please check the Element locator.");
            nsee.printStackTrace();
        }catch (StaleElementReferenceException sere){
            info("\nStale Element Reference Exception Occurred!. Please add some explicit wait.");
            sere.printStackTrace();
        }
    }

    /**
     *
     * @param selectElement is the dropdown element to be used.
     *                       Deselects the dropdown elements at specified index.
     * @param dropDownIndex is the index for the dropdown list
     */
    protected void deselectByIndex(WebElement selectElement, int dropDownIndex){
        try {
            getSelectObject(selectElement).deselectByIndex(dropDownIndex);
            info("Deselected the element at index : "+ dropDownIndex);
        }catch (NoSuchElementException nsee){
            info("\nNo Such Element Exception Occurred!. Please check the Element locator.");
            nsee.printStackTrace();
        }catch (StaleElementReferenceException sere){
            info("\nStale Element Reference Exception Occurred!. Please add some explicit wait.");
            sere.printStackTrace();
        }
    }


    /**
     *
     * @param selectElement is the dropdown element to be used.
     *                       Deselects the dropdown elements with provided value.
     * @param dropdownListValue is the value in the dropdown list.
     */
    protected void deselectByValue(WebElement selectElement, String dropdownListValue){
        try {
            getSelectObject(selectElement).deselectByValue(dropdownListValue);
            info("Deselected the element with value : "+ dropdownListValue);
        }catch (NoSuchElementException nsee){
            info("\nNo Such Element Exception Occurred!. Please check the Element locator.");
            nsee.printStackTrace();
        }catch (StaleElementReferenceException sere){
            info("\nStale Element Reference Exception Occurred!. Please add some explicit wait.");
            sere.printStackTrace();
        }
    }

    /**
     *
     * @param selectElement is the dropdown element to be used.
     *                       Deselects the dropdown elements with provided visibleText.
     * @param visibleText
     */
    protected void deselectByVisibleText(WebElement selectElement, String visibleText){
        try {
            getSelectObject(selectElement).deselectByVisibleText(visibleText);
            info("Deselected the element with visibleText : "+ visibleText);
        }catch (NoSuchElementException nsee){
            info("\nNo Such Element Exception Occurred!. Please check the Element locator.");
            nsee.printStackTrace();
        }catch (StaleElementReferenceException sere){
            info("\nStale Element Reference Exception Occurred!. Please add some explicit wait.");
            sere.printStackTrace();
        }
    }

    /**
     *
     * @param selectElement is the dropdown box element.
     * @param printList is a flag to print the list extracted.
     * @return the List of Extracted DropDown Options.
     */
    protected List<WebElement> getAllSelectedOptions(WebElement selectElement, boolean printList){

        List<WebElement> extractedList =getSelectObject(selectElement).getAllSelectedOptions();
        if(printList==true){
            for (WebElement e: extractedList){
                info(e.getText());
            }
        }
        return extractedList;
    }

    /**
     *
     * @param element that needs to be clicked upon.
     *                This is the native implementation of WebDriver with Exception handling mechanism
     *                and logging information.
     */
    protected void simpleClick(WebElement element){
        try {
            element.click();
            info("Clicked on element");
        }catch (NoSuchElementException nsee){
            info("\nNo Such Element Exception Occurred!. Please check the Element locator.");
            nsee.printStackTrace();
        }catch (StaleElementReferenceException sere){
            info("\nStale Element Reference Exception Occurred!. Please add some explicit wait.");
            sere.printStackTrace();
        }
    }

    /**
     *
     * @param elementToBeClicked the element to clicked with JavaScript Executor.
     *
     */
    protected void clickWithJS(WebElement elementToBeClicked){
        waitForJQueryToComplete();
        waitForPageLoadToComplete();
        try {
            getJsDriver().executeScript("argument[0].click();", elementToBeClicked);
        }catch (JavascriptException jse){
            info("JavaScript Exception Occurred!");
            jse.printStackTrace();
            throw jse;
        }catch (NullPointerException nsee) {
            info("Null Pointer Exception Occurred! . Check you driver is getting initialised!");
            nsee.printStackTrace();
            throw nsee;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param elementToRightClickUpon is the element on which the right click needs to be performed.
     */
    protected void rightClickOnElement(final WebElement elementToRightClickUpon){
        try {
            getActionsHandle().contextClick(elementToRightClickUpon).build().perform();
            info("Right Clicked on WebElement");
        }catch (NoSuchElementException nsee){
            info("\nNo Such Element Exception Occurred!. Please check the Element locator.");
            nsee.printStackTrace();
        }catch (StaleElementReferenceException sere){
            info("\nStale Element Reference Exception Occurred!. Please add some explicit wait.");
            sere.printStackTrace();
        }
    }

    /**
     *
     * @param element element that needs to be right clicked upon.
     * @param option is the option to be selected from the right click options (DOM options only).
     */
    protected void rightClickAndChooseOptionByPartialLinkText(final WebElement element, String option){
        try {
            getActionsHandle().contextClick(element).build().perform();
            WebElement ele = element.findElement(By.partialLinkText(option));
            clickWithJS(ele);
            info("Right Clicked on WebElement");
        }catch (NoSuchElementException nsee){
            info("\nNo Such Element Exception Occurred!. Please check the Element locator.");
            nsee.printStackTrace();
        }catch (StaleElementReferenceException sere){
            info("\nStale Element Reference Exception Occurred!. Please add some explicit wait.");
            sere.printStackTrace();
        }
    }

    /**
     *
     * @param element Element to be Double Clicked.
     */
    protected void doubleClickElement(final WebElement element){
        try {
            explicitWaitForElementToBeVisible(element,20);
            getActionsHandle().moveToElement(element).doubleClick(element).build().perform();
            info("Double Clicked on WebElement");
        }catch (NoSuchElementException nsee){
            info("\nNo Such Element Exception Occurred!. Please check the Element locator.");
            nsee.printStackTrace();
        }catch (StaleElementReferenceException sere){
            info("\nStale Element Reference Exception Occurred!. Please add some explicit wait.");
            sere.printStackTrace();
        }
    }



    // Set Value in Input Elements.


    /**
     *
     * @param inputElement in which value needs to be set.
     * @param inputValue is the value.
     */
    protected void setValueInInputField(final WebElement inputElement, String inputValue){
        try {
            explicitWaitForElementToBeVisible(inputElement,20);
            clickAndClearField(inputElement);
            inputElement.sendKeys(inputValue);
            info("The Value set in field is : "+inputValue);
        }catch (NoSuchElementException nsee){
            info("\nNo Such Element Exception Occurred!. Please check the Element locator.");
            nsee.printStackTrace();
        }catch (StaleElementReferenceException sere){
            info("\nStale Element Reference Exception Occurred!. Please add some explicit wait.");
            sere.printStackTrace();
        }
    }


    /**
     *
     * @param inputElement is the element in which value needs to be set.
     * @param inputValue is the value to be set.
     */
    protected void setValueInInputFieldJS( WebElement inputElement, String inputValue){
        cleanAndRebuildElement(inputElement,150,25);
        getJsDriver().executeScript("arguments[0].value = '';", inputElement);
        inputElement.sendKeys(inputValue);
        info("Value Set in input element is  : " + inputValue);
    }


    /**
     *
     * @param element is the element where the driver will scroll into view.
     *                this method will scroll the page until the element is in view.
     */
    protected void scrollToElement(final WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        info("Scrolling to element in view");
    }

    /**
     *
     * @param inputElement is the elememt  that needs to be cleared. This would be an input field.
     */
    protected void clearInputElement(WebElement inputElement){
        try {
            inputElement.clear();
            info("Cleared the inputElement "+inputElement.toString());
        }catch (NoSuchElementException nsee){
            info("\nNo Such Element Exception Occurred!. Please check the Element locator.");
            nsee.printStackTrace();
        }catch (StaleElementReferenceException sere){
            info("\nStale Element Reference Exception Occurred!. Please add some explicit wait.");
            sere.printStackTrace();
        }
    }

    /**
     *
     * @param elementToBeClickedAndCleared is the element that needs to be clicked and cleared.
     */
    protected void clickAndClearField(WebElement elementToBeClickedAndCleared){
        simpleClick(elementToBeClickedAndCleared);
        clearInputElement(elementToBeClickedAndCleared);
    }

    /**
     *CHECK THIS duplicated
     * @param elementToBeDoubleClicked the element that needs to be double clicked.
     */
    protected void doubleClickWebElement(WebElement elementToBeDoubleClicked){
        Actions actions= getActionsHandle();
        actions.moveToElement(elementToBeDoubleClicked).doubleClick().build().perform();
        info("Double clicked element "+elementToBeDoubleClicked);
    }


    /**
     *
     * @param element is the element that needs to be rebuilt.
     * @return the rebuilt fresh element.
     */
    protected WebElement cleanAndRebuildElement(final WebElement element, int pollMilliseconds){
        WebElement e2 = null;
        int counter = 0 ;
        do{
            try{
                e2=element;
                e2.isDisplayed();
                info("Refreshing WebElement....");
                break;
            }catch (StaleElementReferenceException sere){
                e2=null;
                deadWait(pollMilliseconds);
                counter++;
            }catch(java.util.NoSuchElementException e){
                e2=null;
                deadWait(pollMilliseconds);
                counter++;
            }
        }while(counter<20);
        return e2;
    }


    /**
     *
     * @param element is the element that needs to be rebuilt.
     * @param pollMilliseconds is the time it will poll between rebuilding.
     * @param retryCount is the time it will retry to rebuild the element.
     * @return the rebuilt element
     */
    protected WebElement cleanAndRebuildElement(final WebElement element, int pollMilliseconds, int retryCount){
        WebElement e2 = null;
        int counter = 0 ;
        do{
            try{
                e2=element;
                e2.isDisplayed();
                info("Refreshing WebElement....");
                break;
            }catch (StaleElementReferenceException sere){
                e2=null;
                deadWait(pollMilliseconds);
                counter++;
            }catch(java.util.NoSuchElementException e){
                e2=null;
                deadWait(pollMilliseconds);
                counter++;
            }
        }while(counter<retryCount);
        return e2;
    }

    /**
     * @param element the element on which explicit wait needs to be performed.
     * @param timeInSeconds the time in seconds to be waited upon.
     */
    protected void explicitWaitForElementToBeClickable(WebElement element, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * @param element the element on which explicit wait needs to be performed.
     * @param timeInSeconds the time in seconds to be waited upon.
     */
    protected void explicitWaitForElementToBeVisible(WebElement element, int timeInSeconds) {
        WebDriverWait wait = getWebDriverWait(200);
        wait.until(ExpectedConditions.visibilityOf(element));
        info("Waiting for the WebElement to be visible.");
    }

    /**
     *
     * @param elementToTabOn the WebElement on which the keyboard key needs to be activated.
     * @param keys is the enum Keys to be used.
     */
    public void useKeyboardButton(WebElement elementToTabOn, Keys keys){
        elementToTabOn.sendKeys(keys);
        info("clicked Tab button on Keyboard"+keys.name().toString());
    }

    /**
     *
     * @param elementToBeSelected is the element to be selected.
     *                            Waits for the element to be selected.
     */
    protected void explicitWaitForElementToBeSelected(WebElement elementToBeSelected){
        WebDriverWait wait = getWebDriverWait(200);
        wait.until(ExpectedConditions.elementSelectionStateToBe(elementToBeSelected, true));
        info("Waiting for the WebElement to be Selected.");
    }

    /**
     *
     * @param elementToBeSelected is the element to be selected.
     *                            Waits for the element to be selected.
     */
    protected void explicitWaitForElementNotToBeSelected(WebElement elementToBeSelected){
        WebDriverWait wait = getWebDriverWait(200);
        wait.until(ExpectedConditions.elementSelectionStateToBe(elementToBeSelected, false));
        info("Waiting for the WebElement to be Not Selected.");
    }

    /**
     *
     * @param pageTitle is the Page Title which is loaded.
     */
    protected void waitForPageTitleToBeLoaded(String pageTitle){
        WebDriverWait wait = getWebDriverWait(200);
        wait.until(ExpectedConditions.titleIs(pageTitle));
        info("Waiting for the WebElement to be Not Selected.");
    }

    /**
     *
     * @param expectedPageTitle is the page that needs to be loaded.
     * @return the boolean status of the expected page title and actual page title.
     */
    protected boolean  isCorrectPageIsLoaded(String expectedPageTitle){
        waitForPageTitleToBeLoaded(expectedPageTitle);
        String actualTile = getDriver().getTitle();
        boolean isEqual = actualTile.equals(expectedPageTitle);
        info("The Extracted page title is : "+ actualTile+ "\nThe expected page title is : "+expectedPageTitle+"\nMatched Status is : "+isEqual);
        return isEqual;
    }

    /**
     * @param milliseconds provides waiting mechanism when extra wait is required
     *                 can be used explicitly at desired points. This is Thread.sleep();
     */
    protected void deadWait(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException ie){
            info("Interrupted Exception Occurred !");
            ie.printStackTrace();
        }
    }

    /**
     * This method wais for the page to be laoded ; waits for the document reference from the DOM
     * waits until the refrenced time inside the method. use this after the Save and Next button click to ensure the next page is successfully loaded .
     */
    protected void waitForPageLoadToComplete() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                System.out.println("Current Window State       : "
                        + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }

    /**
     *
     * @return the object of class JavascriptExecutor. This casts the current driver in hand to a JavascriptExecutor Driver.
     */
    protected JavascriptExecutor getJsDriver() {
        JavascriptExecutor jsDriver=null;

        try {
            jsDriver = (JavascriptExecutor) getDriver();
        } catch (ClassCastException cce) {
            info("Class Cast Exception Occurred!. Check if the driver is of type WebDriver");
            cce.printStackTrace();
            throw cce;
        } catch (NullPointerException nsee) {
            info("Null Pointer Exception Occurred! . Check you driver is getting initialised!");
            nsee.printStackTrace();
            throw nsee;
        }
        return jsDriver;
    }

    /**
     * @throws InterruptedException
     * This method is to wait for the JQuery to complete avoiding stale values to be populated.
     */
    protected void waitForJQueryToComplete() {
        while (true) {
            Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor)driver).executeScript("return jQuery.active == 0");
            if (ajaxIsComplete){
                info("Waiting for JQuery to complete");
                break;
            }
        }
    }

    /**
     * This void method returns information about the current method at runtime.
     */
    public void getMeCurrentMethodInfo(){
        Thread.currentThread().getContextClassLoader();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String ClassFromWhereMethodCallIsMade = Thread.currentThread().getStackTrace()[2].getFileName();
        info(   "\n-------------------------------------------------------------------------"+"" +
                "\n|Information for the current running Method                      "+"" +
                "\n|Method Name is             |"+methodName+"                             "+"" +
                "\n|Class Calling this method  |"+ClassFromWhereMethodCallIsMade+"         "+"" +
                "\n-------------------------------------------------------------------------");
    }

    /**
     *
     * @param verticalIndex scrolls to page vertically by index.
     */
    protected void scrollToPageVerticalJS(int verticalIndex){
        getJsDriver().executeScript("window.scrollBy(0,"+verticalIndex+")");
    }

    /**
     *
     * @return the document title by JavaScript Executor.
     */
    protected String getDocumentTitleByJS(){
        return getJsDriver().executeScript("return document.title;").toString();
    }

    /**
     *
     * @return the inner text of a document by JavaScript Executor.
     */
    protected String getInnerTextOfWebPageByJS(){
        return getJsDriver().executeScript("return document.documentElement.innerText; ").toString();
    }

    protected void refreshBrowserWindow(){
        getJsDriver().executeScript("history.go(0)");
        info("Browser Window is getting refreshed");
    }

    /**
     *
     * @return the Array for the inner dimension for the current browser that is open.
     * height and width.
     */
    protected String [] getBrowserInnerDimension(){
        String [] dimension = new String[2];
        dimension [0]= getJsDriver().executeScript("return window.innerHeight;").toString();
        info("The inner height of the browser is : "+dimension[0].toString());
        dimension [1]= getJsDriver().executeScript("return window.innerWidth;").toString();
        info("The inner width of the browser is : "+dimension[1].toString());
        return dimension;
    }



    /**
     *
     * @return The Name of the current Window Handle.
     */
    protected String getCurrentWindowHandle(){
        String handleName = getDriver().getWindowHandle();
        info("The Current Window Handle Name is : "+handleName);
        return handleName;
    }

    /**
     *
     * @return the Set of windows that are present at point in time.
     */
    protected Set<String> getWindowHandles(){
        Set<String> setOfWindowHandles = getDriver().getWindowHandles();
        return setOfWindowHandles;
    }

    protected void selectWindowHandle(String handleName){
        WebDriver driver = getDriver();
        try {
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);

            }
        }catch (NoSuchWindowException nswe){
            info("No Such Window Exception Occurred!");
            throw nswe;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return The current URL of the page.
     */
    protected String getCurrentURL(){
        String currentURL = getDriver().getCurrentUrl().toString();
        info("The Current URL is : "+currentURL);
        return currentURL;
    }

    /**
     * This method performs the WebDriver Delete All Cookies action.
     */
    protected void deleteAllCookies(){
        getDriver().manage().deleteAllCookies();
        info("Deleting all the cookies!.");
    }


    /**
     *
     * @param cookie is the name of the Cookie to be deleted.
     */
    protected void deleteCookiesByCookieName(Cookie cookie){
        WebDriver driver = getDriver();
        driver.manage().deleteCookie(cookie);
        if(driver.manage().getCookieNamed(cookie.toString())==null){
         info("Succesfully Deleted the Cookie : "+cookie.toString());
        }else {
            warn("The cookie named "+cookie.toString()+" is not deleted!");
        }
    }

    /**
     *
     * @param cookieName is the String name of the cookie
     */
    protected void deleteCookiesByCookieName(String cookieName){
        WebDriver driver = getDriver();
        driver.manage().deleteCookieNamed(cookieName);
        if(driver.manage().getCookieNamed(cookieName)==null){
            info("Succesfully Deleted the Cookie : "+cookieName);
        }else{
            warn("The cookie named "+cookieName+" is not deleted!");
        }
    }

    protected Assertion getHardAssertionObject(){
        Assertion hardAssert = new Assertion();
        return hardAssert;
    }


    protected SoftAssert getSoftAssertionObject(){
        SoftAssert softAsert = new SoftAssert();
        return softAsert;

    }

    protected void hardAssertTrue(boolean b , String failureMesssage){
        getHardAssertionObject().assertTrue(b,failureMesssage);
        info("Hard Assertion Successful : "+b);
    }

    protected void hardAssertTrue(boolean b , String failureMesssage, String succesMessage){
        getHardAssertionObject().assertTrue(b,failureMesssage);
        info("Hard Assertion Status is : "+b+" message : "+succesMessage);
    }

    protected void softAssert(boolean b, String failureMessage){
        getSoftAssertionObject().assertTrue(b,failureMessage);
        info("Soft Assertion Successful : "+b);
    }


    protected void softAssert(boolean b, String failureMessage, String successMessage){
        getSoftAssertionObject().assertTrue(b,failureMessage);
        info("Soft Assertion Status is : "+b+" message : "+successMessage);
    }

    /**
     * accepts the alert present.
     */
    protected void acceptAlert(){
        getDriver().switchTo().alert().accept();
        info("Accepting the Alert.");
    }

    /**
     * Dismisses the Alert
     */
    protected void dismissAlert(){
        getDriver().switchTo().alert().dismiss();
        info("Dismissing the Alert.");
    }

    /**
     *
     * @param username is the username for authentication.
     * @param password is the password for authentication.
     */
    protected void alertAuthenticateWithCredentials(String username, String password){
        Credentials credentials = new UserAndPassword(username, password );
        getDriver().switchTo().alert().authenticateUsing(credentials);
        info("Authenticated the Alert using Credentials.");
    }


    /**
     * Gets the text from the Alert.
     */
    protected String alertGetText (){
        String alertText = getDriver().switchTo().alert().getText();
        info("The text extracted from the alert is : "+ alertText);
        return alertText;
    }

    /**
     *
     * @param inputValue is the value to be set in  the alert.
     */
    protected  void alertSetValue (String inputValue){
        getDriver().switchTo().alert().sendKeys(inputValue);
        info("The vaue set in the alert is : "+inputValue);
    }
}
