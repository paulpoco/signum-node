package signum.net;

import brs.props.Props;

public class MockNetwork extends TestnetNetwork {

  public MockNetwork() {

    setProperty(Props.NETWORK_NAME, "Signum-LOCAL-MOCK");
    setProperty(Props.GENESIS_BLOCK_ID, "7255460345239802627");

    setProperty(Props.DEV_OFFLINE, "true");
    setProperty(Props.DEV_MOCK_MINING, "true");

    setProperty(Props.SODIUM_BLOCK_HEIGHT, "0");
    setProperty(Props.SIGNUM_HEIGHT, "0");
    setProperty(Props.POC_PLUS_HEIGHT, "0");
    setProperty(Props.SPEEDWAY_HEIGHT, "0");

    setProperty(Props.BRS_CHECKPOINT_HEIGHT, "-1");
    setProperty(Props.BRS_CHECKPOINT_HASH, "");

    setProperty(Props.DB_URL, "jdbc:h2:file:./db/signum-mock;DB_CLOSE_ON_EXIT=FALSE");
  }
}
