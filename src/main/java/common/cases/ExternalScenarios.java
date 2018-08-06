package common.cases;

import external.github.EditHtmlPage;
import external.github.GitHubSignInPage;

/*Class to allocate common scenarios in external apps (GitHub, Shopify etc).
 * */
public class ExternalScenarios {

    public static void editGiHubFile(String editLink, String user, String pswrd, String codeQuery) {
        CommonScenarios.navigateToUrl(editLink);
        new GitHubSignInPage()
                .login(user, pswrd);
        new EditHtmlPage()
                .setCodeValue(codeQuery)
                .commitChanges();
    }

}
