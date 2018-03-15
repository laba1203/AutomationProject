package talkable.talkableSite.campaign.pages.editorPage.localizationSidebar;

public interface RecordFactory {

    void createABTest();

    void copyToOtherCampaigns();

    String getNameText();

    void update(String newValue);

    String getValueText();
}
