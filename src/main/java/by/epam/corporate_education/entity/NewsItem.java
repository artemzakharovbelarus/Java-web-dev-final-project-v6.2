package by.epam.corporate_education.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class NewsItem {
    @Setter(AccessLevel.PRIVATE)
    private int idNewsItem;
    private String newsIntro;
    private String newsItemText;
    private String newsItemImage;
    private int idAdmin;

    public NewsItem(int idNewsItem, String newsIntro, String newsItemText, String newsItemImage, int idAdmin){
        setIdNewsItem(idNewsItem);
        setNewsIntro(newsIntro);
        setNewsItemText(newsItemText);
        setNewsItemImage(newsItemImage);
        setIdAdmin(idAdmin);
    }
}