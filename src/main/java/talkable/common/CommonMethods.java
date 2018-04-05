package talkable.common;

import org.testng.Assert;

public class CommonMethods {

    public static String getCampaignPlacementString(CampaignPlacement placement){
            String placementName;
            switch (placement){
                case PostPurchase:
                    placementName = "Post Purchase";
                    break;
                case Standalone:
                    placementName = "Standalone";
                    break;
                case Gleam:
                    placementName = "Gleam";
                    break;
                case FloatingWidget:
                    placementName = "Floating Widget";
                    break;
                default:
                    placementName = null;
                    Assert.fail("FAILED: Unknown placement type: " + placement.toString());
            }
            return placementName;
    }

    public static String getCampaignTypeString(CampaignType type){
        switch (type){
            case Invite:
                return "Invite";
            case AdvocateDashboard:
                return "Advocate Dashboard";
            case RewardGleam:
                return "Reward Gleam";
            default:
                Assert.fail("FAILED: Unknown campaign type: " + type.toString());
                return null;
        }
    }
}
