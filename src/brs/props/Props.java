package brs.props;

import brs.Burst;
import brs.Constants;
import brs.Genesis;
import brs.util.Convert;

public class Props {

  public static final Prop<String> APPLICATION = new Prop<>("node.application", Burst.APPLICATION);
  public static final Prop<String> VERSION = new Prop<>("node.version", Burst.VERSION.toString());

  // Structural parameters
  public static final Prop<Integer> BLOCK_TIME = new Prop<>("node.blockTime", 240);
  public static final Prop<Integer> DECIMAL_PLACES = new Prop<>("node.decimalPlaces", 8);
  public static final Prop<Integer> ONE_COIN_NQT = new Prop<>("node.coinFactor", 100_000_000);
  public static final Prop<Integer> API_PORT = new Prop<>("API.Port", 8125);
  public static final Prop<String> NETWORK_NAME = new Prop<>("node.networkName", Constants.SIGNUM_NETWORK_NAME);
  public static final Prop<String> GENESIS_BLOCK_ID = new Prop<>("node.genesisBlockId", Convert.toUnsignedLong(Genesis.GENESIS_BLOCK_ID));
  public static final Prop<String> ADDRESS_PREFIX = new Prop<>("node.addressPrefix", "S");
  public static final Prop<String> VALUE_SUFIX = new Prop<>("node.valueSuffix", "SIGNA");
  public static final Prop<Boolean> EXPERIMENTAL = new Prop<>("node.experimental", false);

  public static final Prop<Integer> BLOCK_REWARD_START = new Prop<>("node.blockRewardStart", 10_000);
  public static final Prop<Integer> BLOCK_REWARD_CYCLE = new Prop<>("node.blockRewardCycle", 10_800);
  public static final Prop<Integer> BLOCK_REWARD_CYCLE_PERCENTAGE = new Prop<>("node.blockRewardCycle", 95);
  public static final Prop<Integer> BLOCK_REWARD_LIMIT_HEIGHT = new Prop<>("node.blockLimitHeight", 972_000);
  public static final Prop<Integer> BLOCK_REWARD_LIMIT_AMOUNT = new Prop<>("node.blockLimitAmount", 100);

  // Transaction fee cash back options
  public static final Prop<String> CASH_BACK_ID = new Prop<>("node.cashBackId", "8952122635653861124");
  public static final Prop<Integer> CASH_BACK_FACTOR = new Prop<>("node.cashBackFactor", 4);

  public static final Prop<String> NETWORK_PARAMETERS = new Prop<>("node.network", null);

  // DEV options
  public static final Prop<Boolean> DEV_OFFLINE = new Prop<>("DEV.Offline", false);
  public static final Prop<Integer> DEV_TIMEWARP    = new Prop<>("DEV.TimeWarp", 1);
  public static final Prop<Boolean> DEV_MOCK_MINING = new Prop<>("DEV.mockMining", false);
  public static final Prop<Integer> DEV_MOCK_MINING_DEADLINE = new Prop<>("DEV.mockMining.deadline", 10);

  public static final Prop<String> DEV_DUMP_PEERS_VERSION = new Prop<>("DEV.dumpPeersVersion", "");

  public static final Prop<Integer> REWARD_RECIPIENT_ENABLE_BLOCK_HEIGHT = new Prop<>("brs.rewardRecipient.startBlock", -1);
  public static final Prop<Integer> DIGITAL_GOODS_STORE_BLOCK_HEIGHT = new Prop<>("brs.digitalGoodsStore.startBlock", -1);
  public static final Prop<Integer> AUTOMATED_TRANSACTION_BLOCK_HEIGHT = new Prop<>("brs.automatedTransactions.startBlock", -1);
  public static final Prop<Integer> AT_FIX_BLOCK_2_BLOCK_HEIGHT = new Prop<>("brs.atFixBlock2.startBlock", -1);
  public static final Prop<Integer> AT_FIX_BLOCK_3_BLOCK_HEIGHT = new Prop<>("brs.atFixBlock3.startBlock", -1);
  public static final Prop<Integer> AT_FIX_BLOCK_4_BLOCK_HEIGHT = new Prop<>("brs.atFixBlock4.startBlock", -1);
  public static final Prop<Integer> PRE_POC2_BLOCK_HEIGHT = new Prop<>("brs.prePoc2.startBlock", -1);
  public static final Prop<Integer> POC2_BLOCK_HEIGHT = new Prop<>("brs.poc2.startBlock", -1);
  public static final Prop<Integer> SODIUM_BLOCK_HEIGHT = new Prop<>("brs.sodium.startBlock", -1);
  public static final Prop<Integer> SIGNUM_HEIGHT = new Prop<>("brs.signum.startBlock", -1);
  public static final Prop<Integer> POC_PLUS_HEIGHT = new Prop<>("brs.pocPlus.startBlock", -1);
  public static final Prop<Integer> SPEEDWAY_HEIGHT = new Prop<>("brs.speedway.startBlock", -1);
  public static final Prop<Integer> SMART_TOKEN_HEIGHT = new Prop<>("brs.smartToken.startBlock", -1);
  public static final Prop<Integer> SMART_FEES_HEIGHT = new Prop<>("brs.smartFees.startBlock", -1);
  
  public static final Prop<Integer> DEV_NEXT_FORK_BLOCK_HEIGHT = new Prop<>("DEV.nextFork.startBlock", -1);

  public static final Prop<Boolean> BRS_DEBUG_TRACE_ENABLED = new Prop<>("brs.debugTraceEnable", false);
  public static final Prop<String> BRS_DEBUG_TRACE_QUOTE = new Prop<>("brs.debugTraceQuote", "\"");
  public static final Prop<String> BRS_DEBUG_TRACE_SEPARATOR = new Prop<>("brs.debugTraceSeparator", "\t");
  public static final Prop<Boolean> BRS_DEBUG_LOG_CONFIRMED = new Prop<>("brs.debugLogUnconfirmed", false);
  public static final Prop<String> BRS_DEBUG_TRACE_ACCOUNTS = new Prop<>("brs.debugTraceAccounts", "");

  public static final Prop<String> BRS_DEBUG_TRACE_LOG = new Prop<>("brs.debugTraceLog", "LOG_AccountBalances_trace.csv");
  public static final Prop<Integer> BRS_COMMUNICATION_LOGGING_MASK = new Prop<>("brs.communicationLoggingMask", 0);

  public static final Prop<Integer> BRS_SHUTDOWN_TIMEOUT = new Prop<>("node.shutdownTimeout", 180);

  public static final Prop<Integer> MAX_INDIRECTS_PER_BLOCK = new Prop<>("node.maxIndirectsPerBlock", 1_200_000);

  // Checkpoint block for faster sync from empty database
  public static final Prop<Integer> BRS_CHECKPOINT_HEIGHT = new Prop<>("node.checkPointHeight", 970_000);
  public static final Prop<String> BRS_CHECKPOINT_HASH = new Prop<>("node.checkPointPrevHash", "c0bb65a25e6fb5f6c4672b5ced900bdf9eb8247187467fd504f42e050fe2ad36");

  // GPU options
  public static final Prop<Boolean> GPU_ACCELERATION     = new Prop<>("GPU.Acceleration", false);
  public static final Prop<Boolean> GPU_AUTODETECT       = new Prop<>("GPU.AutoDetect", true);
  public static final Prop<Integer> GPU_PLATFORM_IDX     = new Prop<>("GPU.PlatformIdx", 0);
  public static final Prop<Integer> GPU_DEVICE_IDX       = new Prop<>("GPU.DeviceIdx", 0);
  public static final Prop<Integer> GPU_UNVERIFIED_QUEUE = new Prop<>("GPU.UnverifiedQueue", 1000);
  public static final Prop<Integer> GPU_HASHES_PER_BATCH = new Prop<>("GPU.HashesPerBatch", 1000);
  public static final Prop<Integer> GPU_MEM_PERCENT      = new Prop<>("GPU.MemPercent", 50);

  // CPU options
  public static final Prop<Integer> CPU_NUM_CORES = new Prop<>("CPU.NumCores", -1);


  // DB options
  public static final Prop<Boolean> DB_SKIP_CHECK  = new Prop<>("DB.SkipCheck", false);
  public static final Prop<String> DB_URL          = new Prop<>("DB.Url", "jdbc:h2:file:./db/signum;DB_CLOSE_ON_EXIT=FALSE");
  public static final Prop<String> DB_USERNAME     = new Prop<>("DB.Username", "");
  public static final Prop<String> DB_PASSWORD     = new Prop<>("DB.Password", "");
  public static final Prop<Integer> DB_CONNECTIONS  = new Prop<>("DB.Connections", 30);
  public static final Prop<Integer> DB_LOCK_TIMEOUT = new Prop<>("DB.LockTimeout", 60);

  public static final Prop<Boolean> DB_TRIM_DERIVED_TABLES = new Prop<>("DB.trimDerivedTables", true);

  public static final Prop<Boolean> BRS_TEST_UNCONFIRMED_TRANSACTIONS = new Prop<>("brs.testUnconfirmedTransactions", false);

  public static final Prop<Boolean> DB_H2_DEFRAG_ON_SHUTDOWN = new Prop<>("Db.H2.DefragOnShutdown", false);

  public static final Prop<Integer> BRS_BLOCK_CACHE_MB = new Prop<>("node.blockCacheMB", 40);

  // P2P options
  public static final Prop<Integer> P2P_PORT = new Prop<>("P2P.Port", 8123);
  public static final Prop<String> P2P_MY_PLATFORM = new Prop<>("P2P.myPlatform", "PC");
  public static final Prop<String> P2P_MY_ADDRESS  = new Prop<>("P2P.myAddress", "");
  public static final Prop<String> P2P_LISTEN      = new Prop<>("P2P.Listen", "0.0.0.0");
  public static final Prop<Boolean> P2P_UPNP        = new Prop<>("P2P.UPnP", true);
  public static final Prop<Boolean> P2P_SHARE_MY_ADDRESS = new Prop<>("P2P.shareMyAddress", true);
  public static final Prop<Boolean> P2P_ENABLE_TX_REBROADCAST = new Prop<>("P2P.enableTxRebroadcast", true);
  public static final Prop<String> P2P_REBROADCAST_TO  = new Prop<>("P2P.rebroadcastTo",
      "157.90.168.219; 162.55.177.176; 162.55.177.197; 177.153.50.43; 77.68.79.251; 51.79.166.253; 158.69.63.213; 139.99.196.163; 45.77.94.168; 188.34.159.176; 5.161.75.89");
  public static final Prop<String> P2P_BOOTSTRAP_PEERS = new Prop<>("P2P.BootstrapPeers",
      "212.98.92.236; 108.61.251.202; 190.15.195.118; 24.96.113.8; 88.64.234.237; 137.135.203.145; 70.108.6.237; 144.91.84.164; 213.32.102.141; 5.196.65.184; 89.163.239.219; 165.227.36.71");
  public static final Prop<Integer> P2P_NUM_BOOTSTRAP_CONNECTIONS = new Prop<>("P2P.NumBootstrapConnections", 4);
  public static final Prop<String> P2P_BLACKLISTED_PEERS = new Prop<>("P2P.BlacklistedPeers", "");
  public static final Prop<Integer> P2P_MAX_CONNECTIONS = new Prop<>("P2P.MaxConnections", 20);
  public static final Prop<Integer> P2P_TIMEOUT_CONNECT_MS = new Prop<>("P2P.TimeoutConnect_ms", 4000);
  public static final Prop<Integer> P2P_TIMEOUT_READ_MS = new Prop<>("P2P.TimeoutRead_ms", 8000);
  public static final Prop<Integer> P2P_BLACKLISTING_TIME_MS = new Prop<>("P2P.BlacklistingTime_ms", 600000);
  public static final Prop<Integer> P2P_MAX_BLOCKS = new Prop<>("P2P.MaxBlocks", 720);

  public static final Prop<Integer> P2P_TIMEOUT_IDLE_MS = new Prop<>("P2P.TimeoutIdle_ms", 30000);

  public static final Prop<Boolean> P2P_USE_PEERS_DB        = new Prop<>("P2P.usePeersDb", true);
  public static final Prop<Boolean> P2P_SAVE_PEERS          = new Prop<>("P2P.savePeers", true);
  public static final Prop<Boolean> P2P_GET_MORE_PEERS      = new Prop<>("P2P.getMorePeers", true);
  public static final Prop<Integer> P2P_GET_MORE_PEERS_THRESHOLD = new Prop<>("P2P.getMorePeersThreshold", 400);

  public static final Prop<Integer> P2P_SEND_TO_LIMIT = new Prop<>("P2P.sendToLimit", 10);

  public static final Prop<Integer> P2P_MAX_UNCONFIRMED_TRANSACTIONS = new Prop<>("P2P.maxUnconfirmedTransactions", 8192);
  public static final Prop<Integer> P2P_MAX_PERCENTAGE_UNCONFIRMED_TRANSACTIONS_FULL_HASH_REFERENCE = new Prop<>("P2P.maxUnconfirmedTransactionsFullHashReferencePercentage", 5);

  public static final Prop<Integer> P2P_MAX_UNCONFIRMED_TRANSACTIONS_RAW_SIZE_BYTES_TO_SEND = new Prop<>("P2P.maxUTRawSizeBytesToSend", 175000);

  // API options
  public static final Prop<Boolean> API_SSL     = new Prop<>("API.SSL", false);
  public static final Prop<Boolean> API_SERVER  = new Prop<>("API.Server", true);
  public static final Prop<String> API_ALLOWED = new Prop<>("API.allowed", "127.0.0.1; localhost; [0:0:0:0:0:0:0:1];");
  public static final Prop<String> API_ADMIN_KEY_LIST = new Prop<>("API.adminKeyList", "");

  public static final Prop<Boolean> API_ACCEPT_SURPLUS_PARAMS = new Prop<>("API.AcceptSurplusParams", false);

  public static final Prop<String> API_LISTEN  = new Prop<>("API.Listen", "127.0.0.1");

  public static final Prop<String> API_UI_DIR  = new Prop<>("API.UI_Dir", "html/ui");
  public static final Prop<String> API_SSL_KEY_STORE_PATH     = new Prop<>("API.SSL_keyStorePath", "keystore");
  public static final Prop<String> API_SSL_KEY_STORE_PASSWORD = new Prop<>("API.SSL_keyStorePassword", "password");
  public static final Prop<String> API_SSL_LETSENCRYPT_PATH = new Prop<>("API.SSL_letsencryptPath", "");
  public static final Prop<Integer> API_SERVER_IDLE_TIMEOUT = new Prop<>("API.ServerIdleTimeout", 30000);
  public static final Prop<Boolean> API_SERVER_ENFORCE_POST = new Prop<>("API.ServerEnforcePOST", true);
  public static final Prop<String> API_ALLOWED_ORIGINS = new Prop<>("API.AllowedOrigins", "*");

  public static final Prop<Boolean> JETTY_API_GZIP_FILTER = new Prop<>("JETTY.API.GzipFilter", true);
  public static final Prop<Integer> JETTY_API_GZIP_FILTER_MIN_GZIP_SIZE = new Prop<>("JETTY.API.GZIPFilter.minGzipSize", 1024);

  public static final Prop<Boolean> JETTY_API_DOS_FILTER = new Prop<>("JETTY.API.DoSFilter", true);
  public static final Prop<String> JETTY_API_DOS_FILTER_MAX_REQUEST_PER_SEC = new Prop<>("JETTY.API.DoSFilter.maxRequestsPerSec", "30");
  public static final Prop<String> JETTY_API_DOS_FILTER_THROTTLED_REQUESTS = new Prop<>("JETTY.API.DoSFilter.throttledRequests", "5");
  public static final Prop<String> JETTY_API_DOS_FILTER_DELAY_MS = new Prop<>("JETTY.API.DoSFilter.delayMs", "500");
  public static final Prop<String> JETTY_API_DOS_FILTER_MAX_WAIT_MS = new Prop<>("JETTY.API.DoSFilter.maxWaitMs", "50");
  public static final Prop<String> JETTY_API_DOS_FILTER_MAX_REQUEST_MS = new Prop<>("JETTY.API.DoSFilter.maxRequestMs", "30000");
  public static final Prop<String> JETTY_API_DOS_FILTER_THROTTLE_MS = new Prop<>("JETTY.API.DoSFilter.throttleMs", "30000");
  public static final Prop<String> JETTY_API_DOS_FILTER_MAX_IDLE_TRACKER_MS = new Prop<>("JETTY.API.DoSFilter.maxIdleTrackerMs", "30000");
  public static final Prop<String> JETTY_API_DOS_FILTER_TRACK_SESSIONS = new Prop<>("JETTY.API.DoSFilter.trackSessions", "false");
  public static final Prop<String> JETTY_API_DOS_FILTER_INSERT_HEADERS = new Prop<>("JETTY.API.DoSFilter.insertHeaders", "true");
  public static final Prop<String> JETTY_API_DOS_FILTER_REMOTE_PORT = new Prop<>("JETTY.API.DoSFilter.remotePort", "false");
  public static final Prop<String> JETTY_API_DOS_FILTER_IP_WHITELIST = new Prop<>("JETTY.API.DoSFilter.ipWhitelist", "127.0.0.1,localhost");
  public static final Prop<String> JETTY_API_DOS_FILTER_MANAGED_ATTR = new Prop<>("JETTY.API.DoSFilter.managedAttr", "true");

  public static final Prop<Boolean> JETTY_P2P_GZIP_FILTER               = new Prop<>("JETTY.P2P.GZIPFilter", true);
  public static final Prop<Integer> JETTY_P2P_GZIP_FILTER_MIN_GZIP_SIZE = new Prop<>("JETTY.P2P.GZIPFilter.minGzipSize", 1024);

  public static final Prop<Boolean> JETTY_P2P_DOS_FILTER = new Prop<>("JETTY.P2P.DoSFilter", true);
  public static final Prop<String> JETTY_P2P_DOS_FILTER_MAX_REQUESTS_PER_SEC = new Prop<>("JETTY.P2P.DoSFilter.maxRequestsPerSec", "30");
  public static final Prop<String> JETTY_P2P_DOS_FILTER_THROTTLED_REQUESTS = new Prop<>("JETTY.P2P.DoSFilter.throttledRequests", "5");
  public static final Prop<String> JETTY_P2P_DOS_FILTER_DELAY_MS = new Prop<>("JETTY.P2P.DoSFilter.delayMs", "500");
  public static final Prop<String> JETTY_P2P_DOS_FILTER_MAX_WAIT_MS = new Prop<>("JETTY.P2P.DoSFilter.maxWaitMs", "50");
  public static final Prop<String> JETTY_P2P_DOS_FILTER_MAX_REQUEST_MS = new Prop<>("JETTY.P2P.DoSFilter.maxRequestMs", "300000");
  public static final Prop<String> JETTY_P2P_DOS_FILTER_THROTTLE_MS = new Prop<>("JETTY.P2P.DoSFilter.throttleMs", "30000");
  public static final Prop<String> JETTY_P2P_DOS_FILTER_MAX_IDLE_TRACKER_MS = new Prop<>("JETTY.P2P.DoSFilter.maxIdleTrackerMs", "30000");
  public static final Prop<String> JETTY_P2P_DOS_FILTER_TRACK_SESSIONS = new Prop<>("JETTY.P2P.DoSFilter.trackSessions", "false");
  public static final Prop<String> JETTY_P2P_DOS_FILTER_INSERT_HEADERS = new Prop<>("JETTY.P2P.DoSFilter.insertHeaders", "true");
  public static final Prop<String> JETTY_P2P_DOS_FILTER_REMOTE_PORT = new Prop<>("JETTY.P2P.DoSFilter.remotePort", "false");
  public static final Prop<String> JETTY_P2P_DOS_FILTER_IP_WHITELIST = new Prop<>("JETTY.P2P.DoSFilter.ipWhitelist", "127.0.0.1,localhost");
  public static final Prop<String> JETTY_P2P_DOS_FILTER_MANAGED_ATTR = new Prop<>("JETTY.P2P.DoSFilter.managedAttr", "true");

  public static final Prop<Boolean> INDIRECT_INCOMING_SERVICE_ENABLE = new Prop<>("node.indirectIncomingService.enable", true);

  public static final Prop<Boolean> AUTO_POP_OFF_ENABLED = new Prop<>("node.autoPopOff.enable", true);

  public static final Prop<Boolean> ENABLE_AT_DEBUG_LOG = new Prop<>("node.ATDebugLog.enable", false);

  public static final Prop<String> SOLO_MINING_PASSPHRASES = new Prop<>("SoloMiningPassphrases", "");
  public static final Prop<Boolean> ALLOW_OTHER_SOLO_MINERS = new Prop<>("AllowOtherSoloMiners", true);

  private Props() { //no need to construct
  }
}
