package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

import static com.sun.xml.internal.ws.util.VersionUtil.compare;


public class ContactCreationTest extends TestBase{
  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("test", "test1", "test1");
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(contact, true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size()+1);

    before.add(contact);
    Comparator<? super ContactData> byLastName = (g1, g2) -> compare(g1.getLastname(), g2.getLastname());
    before.sort(byLastName);
    after.sort(byLastName);
    Assert.assertEquals(before, after);
  }
}
