package talkable.talkableSite;

import talkable.talkableSite.headerFrame.Header;

public abstract class AbstractTalkableSitePage extends AbstractTkblSitePageWithoutHeader{

    public Header header;

    public AbstractTalkableSitePage(){
        setElements();
        header = new Header();
    }


    protected void setElements(){
    }


}
