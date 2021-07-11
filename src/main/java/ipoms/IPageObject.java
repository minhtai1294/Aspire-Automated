package ipoms;

public interface IPageObject<T extends IPageObject> {


    public boolean isTextVisibleOnPage(String text);

    public T untilPageIsCompletedLoaded();

}
