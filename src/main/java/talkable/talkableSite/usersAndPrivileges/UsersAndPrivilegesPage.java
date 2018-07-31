package talkable.talkableSite.usersAndPrivileges;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;

public class UsersAndPrivilegesPage extends AbstractTalkableSitePage{

    private static final By createNewUserLctr = By.xpath("//*[@name='commit']");

    private Element createNewUser = new Element(createNewUserLctr, "Create New User button");

}
