/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.local;

import business.converter.ConverterFactory;
import business.facade.LocalFacade;
import business.MapManager;
import business.facade.NacaoFacade;
import control.WorldFacadeCounselor;
import java.util.SortedMap;
import javafx.scene.image.Image;
import model.Cenario;
import model.Local;
import model.Nacao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import persistenceCommons.SettingsManager;

/**
 *
 * @author jmoura
 */
public class ImageFactory {

    private static final Log log = LogFactory.getLog(MapManager.class);
    private static final LocalFacade localFacade = new LocalFacade();
    private static final NacaoFacade nationFacade = new NacaoFacade();
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
        this.landmarkNames = localFacade.getTerrainLandmarksImage();
    }

    public Image getTerrainImage(Local local) {
        int idxTerrain = ConverterFactory.terrainToIndex(localFacade.getTerrenoCodigo(local));
        final String filename = terrainNames[idxTerrain];
        if (SettingsManager.getInstance().isConfig("MapTiles", "2a", "2b")) {
            //feralonso bordless
            return new Image("/images/mapa/hex_2a_" + filename + ".png");
        } else if (SettingsManager.getInstance().isConfig("MapTiles", "2b", "2b")) {
            //bordless meppa
            return new Image("/images/mapa/hex_2b_" + filename + ".gif");
        } else if (SettingsManager.getInstance().isConfig("MapTiles", "2d", "2b")) {
            //border meppa
            return new Image("/images/mapa/hex_" + filename + ".gif");
        } else if (SettingsManager.getInstance().isConfig("MapTiles", "3d", "2b")) {
            //3d from joao bordless
            return new Image("/images/mapa/hex_" + filename + ".png");
        } else {
            //bordless meppa
            return new Image("/images/mapa/hex_2b_" + filename + ".gif");
        }
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

    public static Image getCityImage(int citySize) {
        //TODO: draw, not image
        return new Image("/images/mapa/cp_" + cityNames[citySize] + ".gif");
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

    public static Image getFogofwarImage() {
        return new Image("/images/mapa/hex_fogofwar.gif");
    }

    public static Image getCombatBigNavyImage() {
        return new Image("/images/combat_blue.png");
    }

    public static Image getCombatImage() {
        return new Image("/images/combat.png");
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

    public static Image getAppIconImage() {
        return new Image("/images/hex_wasteland.png");
    }

    public Image getArmyShield(Nacao nation, Cenario cenario) {
        try {
            return new Image("/images/armies/" + getExercitoStrings(WorldFacadeCounselor.getInstance().getCenario())[nationFacade.getNacaoNumero(nation)]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Image("/images/armies/" + getExercitoStrings(WorldFacadeCounselor.getInstance().getCenario())[0]);
        }
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
}
