package signum.net;

import java.util.Arrays;

import brs.fluxcapacitor.FluxValue;
import brs.fluxcapacitor.FluxValues;
import brs.fluxcapacitor.HistoricalMoments;
import brs.props.Props;

public class TestnetNetwork extends NetworkParametersBase {

  public TestnetNetwork() {

    setProperty(Props.NETWORK_NAME, "Signum-TESTNET");

    setProperty(Props.REWARD_RECIPIENT_ENABLE_BLOCK_HEIGHT, "0");
    setProperty(Props.DIGITAL_GOODS_STORE_BLOCK_HEIGHT, "0");
    setProperty(Props.AUTOMATED_TRANSACTION_BLOCK_HEIGHT, "0");
    setProperty(Props.AT_FIX_BLOCK_2_BLOCK_HEIGHT, "0");
    setProperty(Props.AT_FIX_BLOCK_3_BLOCK_HEIGHT, "0");
    setProperty(Props.AT_FIX_BLOCK_4_BLOCK_HEIGHT, "0");
    setProperty(Props.PRE_POC2_BLOCK_HEIGHT, "0");
    setProperty(Props.POC2_BLOCK_HEIGHT, "0");

    setProperty(Props.SODIUM_BLOCK_HEIGHT, "160620");
    setProperty(Props.SIGNUM_HEIGHT, "269100");
    setProperty(Props.POC_PLUS_HEIGHT, "269700");
    setProperty(Props.SPEEDWAY_HEIGHT, "338090");
    FluxValues.MIN_CAPACITY.updateValueChanges(Arrays.asList(
        new FluxValue.ValueChange<Long>(HistoricalMoments.GENESIS, 1_000L),
        new FluxValue.ValueChange<Long>(HistoricalMoments.SPEEDWAY, 8_000L)
        ));

    setProperty(Props.SMART_TOKEN_HEIGHT, "364400");
    setProperty(Props.SMART_FEES_HEIGHT, "398300");

    setProperty(Props.BRS_CHECKPOINT_HEIGHT, "381300");
    setProperty(Props.BRS_CHECKPOINT_HASH, "f0846a5d9f43801498316011601dd7c90fa7a20bd95295f2a5fba032bcdeb495");

    setProperty(Props.ADDRESS_PREFIX, "TS");
    setProperty(Props.VALUE_SUFIX, "TSIGNA");
    setProperty(Props.EXPERIMENTAL, "true");

    setProperty(Props.P2P_PORT, "7123");
    setProperty(Props.API_PORT, "6876");

    setProperty(Props.P2P_REBROADCAST_TO, "lmsi.club; 162.55.177.197");
    setProperty(Props.P2P_NUM_BOOTSTRAP_CONNECTIONS, "1");

    setProperty(Props.P2P_USE_PEERS_DB, "false");
    setProperty(Props.P2P_SAVE_PEERS, "false");

    setProperty(Props.DB_URL, "jdbc:h2:file:./db/signum-testnet;DB_CLOSE_ON_EXIT=FALSE");

  }
}
