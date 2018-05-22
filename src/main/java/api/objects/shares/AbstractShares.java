package api.objects.shares;

import util.EnvFactory;

public abstract class AbstractShares {

    private static final String OFFERS_URL = EnvFactory.getApiUrl() + "/offers";

    protected String getOffersUrl(){
        return OFFERS_URL;
    }
}
