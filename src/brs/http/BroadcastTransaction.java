package brs.http;

import brs.Burst;
import brs.BurstException;
import brs.Transaction;
import brs.TransactionProcessor;
import brs.props.Props;
import brs.services.ParameterService;
import brs.services.TransactionService;
import brs.util.Convert;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

import static brs.http.common.Parameters.TRANSACTION_BYTES_PARAMETER;
import static brs.http.common.Parameters.TRANSACTION_JSON_PARAMETER;
import static brs.http.common.ResultFields.*;

public final class BroadcastTransaction extends APIServlet.JsonRequestHandler {

  private static final Logger logger = Logger.getLogger(BroadcastTransaction.class.getSimpleName());

  private final TransactionProcessor transactionProcessor;
  private final ParameterService parameterService;
  private final TransactionService transactionService;

  public BroadcastTransaction(TransactionProcessor transactionProcessor, ParameterService parameterService, TransactionService transactionService) {
    super(new APITag[]{APITag.TRANSACTIONS}, TRANSACTION_BYTES_PARAMETER, TRANSACTION_JSON_PARAMETER);

    this.transactionProcessor = transactionProcessor;
    this.parameterService = parameterService;
    this.transactionService = transactionService;
  }

  @Override
  protected
  JsonElement processRequest(HttpServletRequest req) throws BurstException {

    String transactionBytes = Convert.emptyToNull(req.getParameter(TRANSACTION_BYTES_PARAMETER));
    if(transactionBytes == null) {
      // Check the body
      try {
        transactionBytes = Convert.emptyToNull(req.getReader().readLine());
        if(transactionBytes != null) {
          transactionBytes = transactionBytes.replace("\"", "");
        }
      }
      catch (Exception e) {
        transactionBytes = null;
      }
    }
    String transactionJSON = Convert.emptyToNull(req.getParameter(TRANSACTION_JSON_PARAMETER));
    Transaction transaction = parameterService.parseTransaction(transactionBytes, transactionJSON);

    long cashBackId = 0L;
    if(Burst.getPropertyService() != null)
      cashBackId = Convert.parseUnsignedLong(Burst.getPropertyService().getString(Props.CASH_BACK_ID));
    if (transaction.getCashBackId() != cashBackId){
      JsonObject response = new JsonObject();
      response.addProperty(ERROR_CODE_RESPONSE, 4);
      response.addProperty(ERROR_DESCRIPTION_RESPONSE, "Incorrect transactionBytes: cash back ID mismatch");
      throw new ParameterException(response);
    }

    JsonObject response = new JsonObject();
    try {
      transactionService.validate(transaction);
      response.addProperty(NUMBER_PEERS_SENT_TO_RESPONSE, transactionProcessor.broadcast(transaction));
      response.addProperty(TRANSACTION_RESPONSE, transaction.getStringId());
      response.addProperty(FULL_HASH_RESPONSE, transaction.getFullHash());
    } catch (BurstException.ValidationException | RuntimeException e) {
      logger.log(Level.INFO, e.getMessage(), e);
      response.addProperty(ERROR_CODE_RESPONSE, 4);
      response.addProperty(ERROR_DESCRIPTION_RESPONSE, "Incorrect transaction: " + e.toString());
      response.addProperty(ERROR_RESPONSE, e.getMessage());
    }
    return response;

  }

  @Override
  boolean requirePost() {
    return true;
  }

}
