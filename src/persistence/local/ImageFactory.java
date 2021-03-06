/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.local;

import business.facade.LocalFacade;
import business.MapManager;
import business.facade.NacaoFacade;
import control.WorldFacadeCounselor;
import gui.animation.AnimatedImage;
import java.util.SortedMap;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.Cenario;
import model.Nacao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author jmoura
 */
public class ImageFactory {

    private static final Log log = LogFactory.getLog(MapManager.class);
    private static final LocalFacade localFacade = new LocalFacade();
    private static final NacaoFacade nationFacade = new NacaoFacade();
    private final AnimatedImage combatSprite;
    private static final String[] bridgeNames = {"empty", "ponte_no", "ponte_ne", "ponte_l", "ponte_se", "ponte_so", "ponte_o"};
    private static final String[] creekNames = {"empty", "riacho_no", "riacho_ne", "riacho_l", "riacho_se", "riacho_so", "riacho_o"};
    private static final String[] landingNames = {"empty", "water_NW", "water_NE", "water_E", "water_SE", "water_SW", "water_W"};
    private static final String[] riverNames = {"empty", "rio_no", "rio_ne", "rio_l", "rio_se", "rio_so", "rio_o"};
    private static final String[] roadNames = {"empty", "road_no", "road_ne", "road_l", "road_se", "road_so", "road_o"};
    private static final String[] spanNames = {"empty", "vau_no", "vau_ne", "vau_l", "vau_se", "vau_so", "vau_o"};
    private static final String[] terrainNames = {"vazio", "mar", "costa", "litoral", "floresta", "planicie", "montanha", "colinas", "pantano", "deserto", "wasteland", "lago"};
    private static final String[] fortificationNames = {"vazio", "torre", "forte", "castelo", "fortaleza", "cidadela"};
    private static final String[] cityNames = {"vazio", "acampamento", "aldeia", "vila", "burgo", "cidade"};
    private static final String[] hiddenCityNames = {"vazio", "acampamento_hidden", "aldeia_hidden", "vila_hidden", "burgo_hidden", "cidade_hidden"};
    private static final String[] dockNames = {"vazio", "docas", "porto"};
    private final SortedMap<String, String> landmarkNames;
    private static final String[] shieldsLom = {"neutral.png", "lom_corelay.png", "lom_ashimar.png", "lom_gloom.png", "lom_dregrim.png", "lom_despair.png",
        "lom_korkithdodrak.png", "lom_valethor.png", "lom_kor.png", "neutral2.png", "neutral3.png", "lom_wolf.png", "lom_skulkrin.png", "lom_icetrolls.png", "lom_dragon.png"
    };
    private static final String[] shieldsAll = {"neutral.png", "KingsCourt.gif", "Jofrey.png", "Arryn.png", "Baratheon.gif", "Greyjoy.gif", "Lannister.gif",
        "Martell.png", "Stark.gif", "Targaryen.gif", "Tully.png", "Tyrell.gif", "NightsWatch.png", "Bolton.png", "Yronwood.png", "Stannis.gif", "Frey.png",
        "Hightower.gif", "Volantis.png", "Pentos.png", "Braavos.png", "FreeCities.png", "Wildlings.png", "neutral2.png", "neutral3.png",
        "Esparta.gif", "Atenas.gif", "Macedonia.gif", "Persia.gif", "Tracia.gif", "Milletus.gif", "Illyria.gif", "Epirus.gif", "Twainek.gif", "Frusodian.gif",
        "lom_corelay.png", "lom_ashimar.png", "lom_gloom.png", "lom_dregrim.png", "lom_despair.png", "lom_korkithdodrak.png", "lom_valethor.png", "lom_kor.png"
    };
    private static final String[] shieldsElse = {"neutral.png", "KingsCourt.gif", "Arryn.png", "Baratheon.gif", "Greyjoy.gif", "Lannister.gif", "Martell.png",
        "Stark.gif", "Targaryen.gif", "Tully.png", "Tyrell.gif", "NightsWatch.png", "FreeCities.png", "Wildlings.png", "neutral2.png", "neutral3.png", "Bolton.png",
        "Yronwood.png", "Stannis.gif", "Frey.png", "Hightower.gif", "Volantis.png", "Pentos.png", "Jofrey.png", "army1.png", "army2.png", "army3.png", "Esparta.gif",
        "Atenas.gif", "Macedonia.gif", "Persia.gif", "Tracia.gif", "Milletus.gif", "Illyria.gif", "Epirus.gif", "Twainek.gif", "Frusodian.gif", "shield.jpg"
    };
    private static final String[] shieldsWdo = {"neutral.png", "wdor_carndun.gif", "wdor_gram.gif", "wdor_gundabad.png", "wdor_highpass.gif", "wdor_moria.png",
        "wdor_methedras.gif", "wdor_morannon.png", "wdor_dolguldur.png", "wdor_wargriders.gif", "wdor_grey.png", "wdob_rangers.png", "wdob_rivendell.png",
        "wdob_beornings.png", "wdob_silvan.png", "wdob_menlake.png", "wdob_longbeard.gif", "wdob_broadbeams.png", "wdob_stonefist.png", "wdob_ironhelm.png",
        "wdob_blackhammer.png", "wdob_dorwinion.gif", "wdob_halfling.png", "wdob_lindon.png", "wdob_lorien.gif", "wdob_lossoth.png", "wdob_northmen.png",
        "wdob_rohan.gif", "wdob_woodmen.png", "wdob_woses.png", "wdor_baltoch.gif", "wdor_druedain.gif", "wdor_forochel.gif", "wdor_mistygoblin.gif", "wdor_northorcs.png",
        "wdor_raiders.gif", "wdor_variags.gif", "wdor_wainriders.gif", "wdon_neutral1.gif"
    };
    private static final String[] shieldsGot = {"neutral.png", "KingsCourt.gif", "Arryn.png", "Baratheon.gif", "Greyjoy.gif", "Lannister.gif", "Martell.png",
        "Stark.gif", "Targaryen.gif", "Tully.png", "Tyrell.gif", "NightsWatch.png", "FreeCities.png", "Wildlings.png", "neutral2.png", "neutral3.png",
        "Bolton.png", "Yronwood.png", "Stannis.gif", "Frey.png", "Hightower.gif", "Volantis.png", "Pentos.png", "Jofrey.png"
    };
    private static final String[] shieldsGreek = {"neutral.png", "Esparta.gif", "Atenas.gif", "Macedonia.gif", "Persia.gif", "Tracia.gif", "Milletus.gif", "Illyria.gif", "Epirus.gif"};
    private static final String[] shieldsArzhog = {"neutral.png", "Twainek.gif", "Frusodian.gif"};

    public ImageFactory() {
        //TODO wishlist: SVG graphs?
        this.landmarkNames = localFacade.getTerrainLandmarksImage();
        //combat sprite
        combatSprite = new AnimatedImage();
        Image[] imageArray = new Image[6];
        imageArray[0] = new Image("/images/combat.png");
        imageArray[1] = new Image("/images/combat.png");
        imageArray[2] = new Image("/images/combat.png");
        imageArray[3] = new Image("/images/combat.png");
        imageArray[4] = new Image("/images/combat_blue.png");
        imageArray[5] = new Image("/images/combat_green.png");
        combatSprite.frames = imageArray;
        combatSprite.duration = 0.100;
    }

    public static Image getTerrainImage(int idxTerrain, String tileSet) {
        final String filename = terrainNames[idxTerrain];
        switch (tileSet) {
            case "1":
                //bordless meppa
                return new Image("/images/mapa/hex_2b_" + filename + ".gif");
            case "2":
                //border meppa
                return new Image("/images/mapa/hex_" + filename + ".gif");
            case "3":
                //3d from joao bordless
                return new Image("/images/mapa/hex_" + filename + ".png");
            case "4":
                //feralonso bordless
                return new Image("/images/mapa/hex_2a_" + filename + ".png");
            default:
                //bordless meppa
                return new Image("/images/mapa/hex_2b_" + filename + ".gif");
        }
    }

    public static Node getRoadIcon(double barHeight, boolean multiple) {
        return getIconMultiple(barHeight, multiple, ImageFactory.getRoadImage(1), ImageFactory.getRoadImage(3), ImageFactory.getRoadImage(5));
    }

    public static Node getRiverIcon(double barHeight, boolean multiple) {
        return getIconMultiple(barHeight, multiple, ImageFactory.getRiverImage(1), ImageFactory.getRiverImage(3), ImageFactory.getRiverImage(5));
    }

    public static Node getCreekIcon(double barHeight, boolean multiple) {
        return getIconMultiple(barHeight, multiple, ImageFactory.getCreekImage(1), ImageFactory.getCreekImage(3), ImageFactory.getCreekImage(5));
    }

    public static Node getBridgeIcon(double barHeight, boolean multiple) {
        return getIconMultiple(barHeight, multiple, ImageFactory.getBridgeImage(1), ImageFactory.getBridgeImage(3), ImageFactory.getBridgeImage(5));
    }

    public static Node getSpanIcon(double barHeight, boolean multiple) {
        return getIconMultiple(barHeight, multiple, ImageFactory.getSpanImage(1), ImageFactory.getSpanImage(3), ImageFactory.getSpanImage(5));
    }

    public static Node getLandingIcon(double barHeight, boolean multiple) {
        return getIconMultiple(barHeight, multiple, ImageFactory.getLandingImage(1), ImageFactory.getLandingImage(3), ImageFactory.getLandingImage(5));
    }

    public static Node getCityIcon(double barHeight, boolean active) {
        return getIconOnOff(barHeight, active, ImageFactory.getCityImagePlain(1), ImageFactory.getRedxImage());
    }

    public static Node getFortificationIcon(double barHeight, boolean active) {
        return getIconOnOff(barHeight, active, ImageFactory.getFortificationImage(1), ImageFactory.getRedxImage());
    }

    public static Node getArmyIcon(double barHeight, boolean active) {
        return getIconOnOff(barHeight, active, ImageFactory.getArmyShieldIcon(), ImageFactory.getRedxImage());
    }

    public static Node getFogofwarIcon(double barHeight, boolean active) {
        return getIconOnOff(barHeight, active, ImageFactory.getFogofwarIcon(), ImageFactory.getRedxImage());
    }

    public static Node getGridIcon(double barHeight, boolean active) {
        return getIconOnOff(barHeight, active, ImageFactory.getGridIcon(), ImageFactory.getRedxImage());
    }

    public static Node getLandmarkIcon(double barHeight, boolean active) {
        return getIconOnOff(barHeight, active, ImageFactory.getLandmarkIcon(), ImageFactory.getRedxImage());
    }

    public static Node getTrackIcon(double barHeight, boolean active) {
        return getIconOnOff(barHeight, active, ImageFactory.getTrackIcon(), ImageFactory.getRedxImage());
    }

    public static Node getCharacterIcon(double barHeight, boolean active) {
        return getIconOnOff(barHeight, active, ImageFactory.getCharAllyImage(), ImageFactory.getRedxImage());
    }

    public static Node getItemIcon(double barHeight, boolean active) {
        return getIconOnOff(barHeight, active, ImageFactory.getItemLostImage(), ImageFactory.getRedxImage());
    }

    public static Node getCombatsIcon(double barHeight, boolean active) {
        return getIconOnOff(barHeight, active, ImageFactory.getCombatImage(), ImageFactory.getRedxImage());
    }

    public static Node getConfigIcon(double barHeight, boolean active) {
        return getIconOnOff(barHeight, active, ImageFactory.getConfigImage(), ImageFactory.getRedxImage());
    }

    public static Node getOverrunIcon(double barHeight, boolean active) {
        return getIconOnOff(barHeight, active, ImageFactory.getExplosionImage(), ImageFactory.getRedxImage());
    }

    private static Node getIconOnOff(double barHeight, boolean active, final Image image1, final Image image2) {
        //render roads
        ImageView img1 = new ImageView(image1);
        img1.setPreserveRatio(true);
        img1.setFitHeight(barHeight);
        img1.setSmooth(true);
        img1.setCache(true);
        if (active) {
            DropShadow shadow = new DropShadow();
            img1.setEffect(shadow);
            return img1;
        }
        ImageView img2 = new ImageView(image2);
        img2.setPreserveRatio(true);
        img2.setFitHeight(barHeight);
        img2.setSmooth(true);
        img2.setCache(true);
        final StackPane stackPane = new StackPane(img1, img2);
        return stackPane;
    }

    private static Node getIconMultiple(double barHeight, boolean multiple, final Image image1, final Image image2, final Image image3) {
        //render roads
        ImageView img1 = new ImageView(image1);
        img1.setPreserveRatio(true);
        img1.setFitHeight(barHeight);
        img1.setSmooth(true);
        img1.setCache(true);
        if (!multiple) {
            return img1;
        }
        ImageView img2 = new ImageView(image2);
        img2.setPreserveRatio(true);
        img2.setFitHeight(barHeight);
        img2.setSmooth(true);
        img2.setCache(true);
        ImageView img3 = new ImageView(image3);
        img3.setPreserveRatio(true);
        img3.setFitHeight(barHeight);
        img3.setSmooth(true);
        img3.setCache(true);
        DropShadow shadow = new DropShadow();
        img1.setEffect(shadow);
        img2.setEffect(shadow);
        img3.setEffect(shadow);
        final StackPane stackPane = new StackPane(img1, img2, img3);
        return stackPane;
    }

    public static Node getArmyShieldIcon(double barHeight, boolean multiple, Cenario scenario) {
        final Image armyShield = ImageFactory.getArmyShieldIcon();
        //render shields
        ImageView img1 = new ImageView(armyShield);
        img1.setPreserveRatio(true);
        img1.setFitHeight(barHeight);
        img1.setSmooth(true);
        img1.setCache(true);
        if (!multiple) {
            return img1;
        }
        ImageView img2 = new ImageView(armyShield);
        img2.setPreserveRatio(true);
        img2.setFitHeight(barHeight / 3);
        img2.setSmooth(true);
        img2.setCache(true);
        img2.setTranslateX(-barHeight / 3);
        img2.setTranslateY(barHeight / 3);
        ImageView img3 = new ImageView(armyShield);
        img3.setPreserveRatio(true);
        img3.setFitHeight(barHeight / 3);
        img3.setSmooth(true);
        img3.setCache(true);
        img3.setTranslateX(barHeight / 3);
        img3.setTranslateY(barHeight / 3);
        DropShadow shadow = new DropShadow();
        img1.setEffect(shadow);
        img2.setEffect(shadow);
        img3.setEffect(shadow);
        final StackPane stackPane = new StackPane(img1, img2, img3);
        return stackPane;
    }

    public static Image getRoadImage(int direcao) {
        return new Image("/images/mapa/hex_" + roadNames[direcao] + ".gif");
    }

    public static Image getRiverImage(int direcao) {
        return new Image("/images/mapa/hex_" + riverNames[direcao] + ".gif");
    }

    public static Image getCreekImage(int direcao) {
        return new Image("/images/mapa/hex_" + creekNames[direcao] + ".gif");
    }

    public static Image getBridgeImage(int direcao) {
        return new Image("/images/mapa/hex_" + bridgeNames[direcao] + ".gif");
    }

    public static Image getSpanImage(int direcao) {
        return new Image("/images/mapa/hex_" + spanNames[direcao] + ".gif");
    }

    public static Image getLandingImage(int direcao) {
        return new Image("/images/mapa/hex_" + landingNames[direcao] + ".gif");
    }

    public static Image getFortificationImage(int fortificationSize) {
        return new Image("/images/mapa/cp_" + fortificationNames[fortificationSize] + ".gif");
    }

    public static Image getCityImagePlain(int citySize) {
        return new Image("/images/mapa/cp_" + cityNames[citySize] + ".gif");
    }

    public static Image getCityImagePainted(int citySize, Color oldFillColor, Color newFillColor, Color oldBorderColor, Color newBorderColor) {
        //TODO wishlist: use sprites and better 3d? icons OR draw, not image
        return reColor(
                new Image("/images/mapa/cp_" + cityNames[citySize] + ".gif"),
                oldFillColor, newFillColor,
                oldBorderColor, newBorderColor
        );
    }

    public static Image getHiddenCityImage(int citySize) {
        return new Image("/images/mapa/cp_" + hiddenCityNames[citySize] + ".gif");
    }

    public static Image getDockImage(int dockSize) {
        return new Image("/images/mapa/cp_" + dockNames[dockSize] + ".gif");
    }

    public Image getLandmark(String cdFeature) {
        return new Image(this.landmarkNames.get(cdFeature));
    }

    public static Image getCapitalImage() {
        return new Image("/images/mapa/cp_capital.gif");
    }

    public static Image getCharOtherImage() {
        return new Image("/images/mapa/hex_pers_other.gif");
    }

    public static Image getCharAllyImage() {
        return new Image("/images/mapa/hex_pers_ally.gif");
    }

    public static Image getCharImage() {
        return new Image("/images/mapa/hex_personagem.gif");
    }

    public static Image getCharNpcImage() {
        return new Image("/images/mapa/hex_npc.gif");
    }

    public static Image getItemLostImage() {
        return new Image("/images/mapa/hex_artefato.gif");
    }

    public static Image getItemKnownImage() {
        return new Image("/images/mapa/hex_artefatoKnown.gif");
    }

    public static Image getGoldmineImage() {
        return new Image("/images/mapa/hex_goldmine.gif");
    }

    public static Image getShipsImage() {
        return new Image("/images/mapa/hex_navio.gif");
    }

    public static Image getTagImage() {
        return new Image("/images/mapa/hex_tag.gif");
    }

    public static Image getTerrainUnknownImage() {
        return new Image("/images/mapa/hex_fogofwar.gif");
    }

    public static Image getCombatBigNavyImage() {
        return new Image("/images/combat_blue.png");
    }

    public static Image getCombatImage() {
        return new Image("/images/combat.png");
    }

    public Image getCombatAnimated(double time) {
        return this.combatSprite.getFrame(time);
    }

    public static Image getConfigImage() {
        return new Image("/images/config.jpg");
    }

    public static Image getBlueBallImage() {
        return new Image("/images/piemenu/dark_blue_button.png");
    }

    public static Image getYellowBallImage() {
        return new Image("/images/piemenu/yellow_button.png");
    }

    public static Image getCombatBigArmyImage() {
        return new Image("/images/combat_green.png");
    }

    public static Image getExplosionImage() {
        return new Image("/images/explosion.png");
    }

    public static Image getRedxImage() {
        return new Image("/images/redx.png");
    }

    public static Image getAppIconImage() {
        return new Image("/images/hex_wasteland.png");
    }

    public static Image getArmyShieldIcon() {
        return new Image("/images/armies/wdob_silvan.png");
    }

    public static Image getArmyShield(Nacao nation, Cenario cenario) {
        try {
            return new Image("/images/armies/" + getExercitoStrings(WorldFacadeCounselor.getInstance().getCenario())[nationFacade.getNacaoNumero(nation)]);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return new Image("/images/armies/" + getExercitoStrings(WorldFacadeCounselor.getInstance().getCenario())[0]);
        }
    }

    public static Image getFogofwarIcon() {
        return new Image("/images/hex_fog.png");
    }

    public static Image getGridIcon() {
        return new Image("/images/grid.png");
    }

    public static Image getLandmarkIcon() {
        return new Image("/images/mapa/feature_tower.gif");
    }

    public static Image getTrackIcon() {
        return new Image("/images/hex_path_army.png");
    }

    public static Image getIconChart() {
        return new Image("/images/bargraph icon.png");
    }

    private static String[] getExercitoStrings(Cenario scenario) {
        if (scenario == null) {
            return shieldsAll;
        } else if (scenario.isGrecia()) {
            return shieldsGreek;
        } else if (scenario.isArzhog()) {
            return shieldsArzhog;
        } else if (scenario.isGot()) {
            return shieldsGot;
        } else if (scenario.isWdo()) {
            return shieldsWdo;
        } else if (scenario.isLom()) {
            return shieldsLom;
        } else {
            return shieldsElse;
        }
    }

    /**
     * reColor the given InputImage to the given color inspired by https://stackoverflow.com/a/12945629/1497139
     *
     * @param inputImage
     * @param oldColor
     * @param newColor
     * @return reColored Image
     *
     */
    public static Image reColor(Image inputImage, Color oldColor, Color newColor) {
        int W = (int) inputImage.getWidth();
        int H = (int) inputImage.getHeight();
        WritableImage outputImage = new WritableImage(W, H);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();
        int ob = (int) (oldColor.getBlue() * 255);
        int or = (int) (oldColor.getRed() * 255);
        int og = (int) (oldColor.getGreen() * 255);
        int nb = (int) (newColor.getBlue() * 255);
        int nr = (int) (newColor.getRed() * 255);
        int ng = (int) (newColor.getGreen() * 255);
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                int argb = reader.getArgb(x, y);
                int a = (argb >> 24) & 0xFF;
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;
                if (r > 200) {
                    //log.info(String.format("%s %s %s", r, g, b));
                }
                if (g == og && r == or && b == ob) {
                    r = nr;
                    g = ng;
                    b = nb;
                }

                argb = (a << 24) | (r << 16) | (g << 8) | b;
                writer.setArgb(x, y, argb);
            }
        }
        return outputImage;
    }

    /**
     * reColor the given InputImage to the given color inspired by https://stackoverflow.com/a/12945629/1497139
     *
     * @param inputImage
     * @param oldFillColor
     * @param newFillColor
     * @param oldBorderColor
     * @param newBorderColor
     * @return reColored Image
     *
     */
    public static Image reColor(Image inputImage, Color oldFillColor, Color newFillColor, Color oldBorderColor, Color newBorderColor) {
        int W = (int) inputImage.getWidth();
        int H = (int) inputImage.getHeight();
        WritableImage outputImage = new WritableImage(W, H);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();
        int ofb = (int) (oldFillColor.getBlue() * 255);
        int ofr = (int) (oldFillColor.getRed() * 255);
        int ofg = (int) (oldFillColor.getGreen() * 255);
        int nfb = (int) (newFillColor.getBlue() * 255);
        int nfr = (int) (newFillColor.getRed() * 255);
        int nfg = (int) (newFillColor.getGreen() * 255);
        int obb = (int) (oldBorderColor.getBlue() * 255);
        int obr = (int) (oldBorderColor.getRed() * 255);
        int obg = (int) (oldBorderColor.getGreen() * 255);
        int nbb = (int) (newBorderColor.getBlue() * 255);
        int nbr = (int) (newBorderColor.getRed() * 255);
        int nbg = (int) (newBorderColor.getGreen() * 255);
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                int argb = reader.getArgb(x, y);
                int a = (argb >> 24) & 0xFF;
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;
                if (r > 200) {
                    //log.info(String.format("%s %s %s", r, g, b));
                }
                if (g == ofg && r == ofr && b == ofb) {
                    r = nfr;
                    g = nfg;
                    b = nfb;
                } else if (g == obg && r == obr && b == obb) {
                    r = nbr;
                    g = nbg;
                    b = nbb;
                }

                argb = (a << 24) | (r << 16) | (g << 8) | b;
                writer.setArgb(x, y, argb);
            }
        }
        return outputImage;
    }
}
