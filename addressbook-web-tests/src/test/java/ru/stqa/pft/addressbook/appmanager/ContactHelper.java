package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver driver) {
    super(driver);
  }
  public void submitContactCreation() {click(By.xpath("//div[@id='content']/form/input[21]"));
  }
  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());

    if (creation){
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
  public void initContactCreation() { click(By.linkText("add new"));
  }
  public void selectContact(){ click(By.name("selected[]"));
  }
  public void initContactModification(){
    click(By.xpath("//img[@alt='Edit']"));
  }
  public void submitContactModification(){
    click(By.name("update"));
  }
  public void initContactDeletion(){
    click(By.xpath("//input[@value='Delete']"));
  }
  public void submitContactDeletion(){
    driver.switchTo().alert().accept();
  }
  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
  }
  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    for (WebElement element : elements){
      String lastname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String firstname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      ContactData contact = new ContactData(firstname, lastname,null);
      contacts.add(contact);
    }
    return contacts;
  }
}