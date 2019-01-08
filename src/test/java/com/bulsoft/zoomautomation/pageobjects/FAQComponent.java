package com.bulsoft.zoomautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FAQComponent extends BasePageObject {

    WebDriverWait wait;

    @FindBy(id = "question")
    private WebElement questionInput;

    @FindBy(css ="button[ng-click='$ctrl.search(1)']")
    private WebElement askUsBtn;

    @FindBy(css = "a[class ~='td-help-topics-link']")
    private WebElement popularQuestionsLink;

    @FindBy(xpath = "//a/span[.='View more']")
    private WebElement popularQuestionsViewMore;

    private WebElement errorMessage;

    private List<WebElement> popularQuestions;

    private WebElement detailedQuestionView;

    public FAQComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
        // Close alert here it self
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ensCall"))).click();
    }


    public FAQComponent clickOnPopularQuestion(){
        this.popularQuestionsLink.click();
        popularQuestions = this.driver.findElements(By.cssSelector("div[question-expand]"));
        return this;
    }

    public FAQComponent clickOnQuestionByIndex(int questionIndex){
        String selector = "div[question-expand]:nth-child("+questionIndex+")";
        detailedQuestionView = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
        detailedQuestionView.click();
        return this;

    }

    public FAQComponent searchQuestion(String question){
        this.questionInput.sendKeys(question);
        this.askUsBtn.click();


        this.errorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class~='no-answer']")));
        return this;
    }


    public WebElement getErrorMessage() {
        return errorMessage;
    }
}
