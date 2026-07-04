package ch.wiss.mealmapbackend.config;

import ch.wiss.mealmapbackend.model.Ingredient;
import ch.wiss.mealmapbackend.model.Recipe;
import ch.wiss.mealmapbackend.repository.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Befüllt die Datenbank beim Start mit Beispiel-REzepten,
 * sofern noch keine Daten vorhanden sind.
 */
@Component
public class RecipeDataSeeder implements CommandLineRunner {

    private final RecipeRepository recipeRepository;

    public RecipeDataSeeder(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) {
        if (recipeRepository.count()> 0) {
            return;
        }
        // 1. Blumenkohlcremesuppe
        Recipe blumenkohlsuppe = new Recipe(
                "Blumenkohlcremesuppe",
                "Klassische cremige Suppe aus Blumenkohl, verfeinert mit Vollrahm",
                "Suppe",
                "Lauch, Zwiebeln und Knollensellerie kleinwuerfelig schneiden. Blumenkohl waschen, " +
                        "einen Teil in Roeschen schneiden und daempfen, Rest in Stuecke schneiden. " +
                        "Matignon in Butter duensten, Blumenkohl beigeben und mitduensten. Mit Mehl staeuben, " +
                        "mit Gemuesefond aufgiessen und aufkochen. Wuerzen und 30 Minuten sieden. Pueriseren, " +
                        "durch ein Sieb passieren, nochmals aufkochen und mit Vollrahm verfeinern."
        );
        blumenkohlsuppe.addIngredient(new Ingredient("Butter", new BigDecimal("30"), "g"));
        blumenkohlsuppe.addIngredient(new Ingredient("Zwiebeln, geschaelt", new BigDecimal("80"), "g"));
        blumenkohlsuppe.addIngredient(new Ingredient("Lauch, gebleicht, geruestet", new BigDecimal("90"), "g"));
        blumenkohlsuppe.addIngredient(new Ingredient("Knollensellerie, geschaelt", new BigDecimal("30"), "g"));
        blumenkohlsuppe.addIngredient(new Ingredient("Blumenkohl, geruestet", new BigDecimal("700"), "g"));
        blumenkohlsuppe.addIngredient(new Ingredient("Weissmehl", new BigDecimal("70"), "g"));
        blumenkohlsuppe.addIngredient(new Ingredient("Gemuesefond", new BigDecimal("2000"), "g"));
        blumenkohlsuppe.addIngredient(new Ingredient("Salz", new BigDecimal("5"), "g"));
        blumenkohlsuppe.addIngredient(new Ingredient("Pfeffer, weiss", new BigDecimal("2"), "g"));
        blumenkohlsuppe.addIngredient(new Ingredient("Vollrahm, 35%", new BigDecimal("80"), "g"));

        // 2. Glasierte Karotten
        Recipe karotten = new Recipe(
                "Glasierte Karotten",
                "Karottenstaebchen, in Butter geduenstet und glasiert",
                "Gemuese",
                "Geschaelte Karotten in Staebchen schneiden. Butter in einer Sauteuse schmelzen, Karotten " +
                        "beigeben und duensten. Mit Zucker und Salz wuerzen. Mit Gemuesefond aufgiessen und " +
                        "zugedeckt knapp weich duensten. Deckel entfernen und die Karotten durch Schwenken " +
                        "fertig duensten und glasieren."
        );

        karotten.addIngredient(new Ingredient("Butter", new BigDecimal("50"), "g"));
        karotten.addIngredient(new Ingredient("Karotten, geschaelt", new BigDecimal("1200"), "g"));
        karotten.addIngredient(new Ingredient("Zucker", new BigDecimal("10"), "g"));
        karotten.addIngredient(new Ingredient("Salz", new BigDecimal("5"), "g"));
        karotten.addIngredient(new Ingredient("Gemuesefond", new BigDecimal("200"), "g"));


        // 3. Geschnetzeltes Kalbfleisch Zürcher Art
        Recipe geschnetzeltes = new Recipe(
                "Geschnetzeltes Kalbfleisch Zuercher Art",
                "Klassisches Zuercher Geschnetzeltes mit Champignon-Rahmsauce",
                "Fleisch",
                "Kalbfleisch in feine Scheiben schneiden, Zwiebeln fein hacken, Champignons in Scheibchen " +
                        "schneiden. Fleisch mit Mehl bestaeuben und im heissen Oel sautieren, herausnehmen und " +
                        "wuerzen. Butter beigeben, Zwiebeln anduensten, Champignons mitduensten. Mit Weisswein " +
                        "abloeschen und einkochen. Demi-glace beigeben und einkochen, Rahm beigeben. Fleisch " +
                        "wieder beigeben, erhitzen ohne aufzukochen, geschlagenen Rahm untermischen und mit " +
                        "Petersilie bestreuen."
        );

        geschnetzeltes.addIngredient(new Ingredient("Kalbshuft, dressiert", new BigDecimal("1400"), "g"));
        geschnetzeltes.addIngredient(new Ingredient("Weissmehl", new BigDecimal("20"), "g"));
        geschnetzeltes.addIngredient(new Ingredient("Sonnenblumenoel, high oleic", new BigDecimal("75"), "g"));
        geschnetzeltes.addIngredient(new Ingredient("Gewuerzsalzmischung fuer Fleisch", new BigDecimal("15"), "g"));
        geschnetzeltes.addIngredient(new Ingredient("Butter", new BigDecimal("80"), "g"));
        geschnetzeltes.addIngredient(new Ingredient("Zwiebeln, geschaelt", new BigDecimal("80"), "g"));
        geschnetzeltes.addIngredient(new Ingredient("Champignons, frisch, geruestet", new BigDecimal("500"), "g"));
        geschnetzeltes.addIngredient(new Ingredient("Weisswein", new BigDecimal("300"), "g"));
        geschnetzeltes.addIngredient(new Ingredient("Demi-glace", new BigDecimal("300"), "g"));
        geschnetzeltes.addIngredient(new Ingredient("Vollrahm, 35%", new BigDecimal("300"), "g"));
        geschnetzeltes.addIngredient(new Ingredient("Petersilie, frisch, gekraust", new BigDecimal("10"), "g"));
        geschnetzeltes.addIngredient(new Ingredient("Salz", new BigDecimal("5"), "g"));
        geschnetzeltes.addIngredient(new Ingredient("Pfeffer, weiss", new BigDecimal("2"), "g"));

        // 4. Kartoffelgratin
        Recipe kartoffelgratin = new Recipe(
                "Kartoffelgratin",
                "Klassisches Gratin dauphinois mit Gruyere und Sbrinz",
                "Kartoffelgerichte",
                "Kartoffeln in duenne Scheiben schneiden, Knoblauch fein hacken. Gratinplatte mit Butter " +
                        "ausstreichen und mit Knoblauch ausstreuen. Vollmilch und Vollrahm aufkochen, mit Salz, " +
                        "Pfeffer und Muskat abschmecken. Kartoffelscheiben beigeben und aufkochen. Die Haelfte " +
                        "des Kaeses untermischen und in die Gratinplatte geben. Mit restlichem Kaese bestreuen, " +
                        "mit Butter betraeufeln und bei 160 Grad ca. 25 Minuten backen, anschliessend bei 220 " +
                        "Grad gratinieren."
        );

        kartoffelgratin.addIngredient(new Ingredient("Kartoffeln, mehligkochend, geschaelt", new BigDecimal("1150"), "g"));
        kartoffelgratin.addIngredient(new Ingredient("Knoblauch, geschaelt", new BigDecimal("5"), "g"));
        kartoffelgratin.addIngredient(new Ingredient("Vollmilch", new BigDecimal("400"), "g"));
        kartoffelgratin.addIngredient(new Ingredient("Vollrahm, 35%", new BigDecimal("150"), "g"));
        kartoffelgratin.addIngredient(new Ingredient("Salz", new BigDecimal("10"), "g"));
        kartoffelgratin.addIngredient(new Ingredient("Pfeffer, weiss", new BigDecimal("2"), "g"));
        kartoffelgratin.addIngredient(new Ingredient("Muskatnuss, gerieben", new BigDecimal("1"), "g"));
        kartoffelgratin.addIngredient(new Ingredient("Gruyere, gerieben", new BigDecimal("65"), "g"));
        kartoffelgratin.addIngredient(new Ingredient("Sbrinz, gerieben", new BigDecimal("65"), "g"));
        kartoffelgratin.addIngredient(new Ingredient("Butter", new BigDecimal("40"), "g"));

        // 5. Vanillegipfel
        Recipe vanillegipfel = new Recipe(
                "Vanillegipfel",
                "Feine Mandel-Vanille-Guetzli, im Vanillezucker gewendet",
                "Konfekt",
                "Vanilleschoten laengs halbieren und Mark auskratzen. Butter und Puderzucker vermischen, " +
                        "Eigelb, Vanillemark und Salz beigeben. Gemahlene Mandeln und Mehl beigeben und kurz " +
                        "zu einem Teig zusammenwirken. Teig zu Stangen rollen, in Stuecke von 10-12g schneiden " +
                        "und zu Gipfeln rollen. Bei 160-170 Grad 12-15 Minuten backen und im warmen Zustand im " +
                        "Vanillezucker wenden."
        );

        vanillegipfel.addIngredient(new Ingredient("Butter", new BigDecimal("275"), "g"));
        vanillegipfel.addIngredient(new Ingredient("Puderzucker", new BigDecimal("125"), "g"));
        vanillegipfel.addIngredient(new Ingredient("Eigelb, pasteurisiert", new BigDecimal("70"), "g"));
        vanillegipfel.addIngredient(new Ingredient("Vanilleschoten", new BigDecimal("3"), "Stueck"));
        vanillegipfel.addIngredient(new Ingredient("Salz", new BigDecimal("1"), "g"));
        vanillegipfel.addIngredient(new Ingredient("Mandelkerne, geschaelt, gemahlen", new BigDecimal("135"), "g"));
        vanillegipfel.addIngredient(new Ingredient("Weissmehl, Typ 550", new BigDecimal("385"), "g"));
        vanillegipfel.addIngredient(new Ingredient("Puderzucker fuer Vanillezucker", new BigDecimal("150"), "g"));
        vanillegipfel.addIngredient(new Ingredient("Vanilleschoten fuer Vanillezucker", new BigDecimal("2"), "Stueck"));

        // 6. Zitronencake
        Recipe zitronencake = new Recipe(
                "Zitronencake",
                "Saftiger Zitronencake, getraenkt mit Zitronensirup",
                "Kuchen",
                "Cakeform mit Butter ausstreichen. Zucker und Wasser aufkochen, erkalten lassen und " +
                        "Zitronensaft beigeben fuer den Sirup. Butter, Zucker, Zitronenzesten und Salz schaumig " +
                        "ruehren. Vollei beigeben und weiter schaumig ruehren. Mehl und Backpulver unterziehen. " +
                        "Masse in die Form abfuellen und bei 190-200 Grad ca. 45-60 Minuten backen. Nach dem " +
                        "Backen Loecher einstechen und mit Zitronensirup traenken."
                        );

        zitronencake.addIngredient(new Ingredient("Butter fuer Form", new BigDecimal("20"), "g"));
        zitronencake.addIngredient(new Ingredient("Butter", new BigDecimal("170"), "g"));
        zitronencake.addIngredient(new Ingredient("Zucker", new BigDecimal("170"), "g"));
        zitronencake.addIngredient(new Ingredient("Zitronenzesten", new BigDecimal("6"), "g"));
        zitronencake.addIngredient(new Ingredient("Salz", new BigDecimal("0.5"), "g"));
        zitronencake.addIngredient(new Ingredient("Vollei, pasteurisiert", new BigDecimal("170"), "g"));
        zitronencake.addIngredient(new Ingredient("Weissmehl, Typ 550", new BigDecimal("170"), "g"));
        zitronencake.addIngredient(new Ingredient("Backpulver", new BigDecimal("6"), "g"));
        zitronencake.addIngredient(new Ingredient("Zucker fuer Sirup", new BigDecimal("25"), "g"));
        zitronencake.addIngredient(new Ingredient("Wasser fuer Sirup", new BigDecimal("25"), "g"));
        zitronencake.addIngredient(new Ingredient("Zitronensaft, frisch", new BigDecimal("60"), "g"));

        recipeRepository.save(blumenkohlsuppe);
        recipeRepository.save(karotten);
        recipeRepository.save(geschnetzeltes);
        recipeRepository.save(kartoffelgratin);
        recipeRepository.save(vanillegipfel);
        recipeRepository.save(zitronencake);
    }
}
