package talkable.talkableSite;

import abstractObjects.AbstractElementsContainer;
import talkable.talkableSite.headerFrame.Header;

public abstract class AbstractTalkableSitePage extends AbstractElementsContainer{

    public Header header;

    public AbstractTalkableSitePage(){
        header = new Header();
    }
}
