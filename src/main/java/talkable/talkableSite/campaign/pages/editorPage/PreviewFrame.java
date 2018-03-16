package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.AbstractTalkableFrame;
import abstractObjects.Element;
import org.openqa.selenium.By;

class PreviewFrame extends AbstractTalkableFrame{

    private static final By frameLocator = By.xpath("//iframe");
//    private boolean secondFrame = false;

    PreviewFrame(){
        setWebElement(frameLocator);
        switchToThisFrame();
        switchToParentFrame();

//        try{
//            setWebElement(frameLocator);
//            secondFrame = true;
//            System.out.println("DEBAG: Iframe inside iframe");
//        }
//        catch (NoSuchElementException e){
//            System.out.println("LOG: There're not iframe inside of this iframe");
//        }
//        switchToParentFrame();
//        System.out.println("DEBAG: Switched to parent iframe");
//        if(secondFrame){
//            switchToParentFrame();
//            System.out.println("DEBAG: Switched to parent window");
//        }
    }


    public String getElementText(By locator){
        switchToThisFrame();
        String out = new Element(locator).getText();
        switchToParentFrame();
        return out;
    }

//    private void switchToChildFrame(){
//        if(secondFrame){
//            switchToThisFrame();
//        }
//    }
//
//    private void switchToParentIFrame(){
//        if(secondFrame){
//            switchToParentFrame();
//        }
//    }

//    public void addElement(String elementText){
//        switchToThisFrame();
//        elements.add(new Element(By.linkText(elementText)));
//        switchToParentFrame();
//    }

//    public ArrayList<Element> getElements() {
//        return elements;
//    }
}
