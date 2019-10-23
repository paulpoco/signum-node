package brs.transaction.digitalGoods

import brs.*
import brs.util.toJsonString
import com.google.gson.JsonObject
import java.nio.ByteBuffer

class DigitalGoodsListing(dp: DependencyProvider) : DigitalGoods(dp) {
    override val subtype = SUBTYPE_DIGITAL_GOODS_LISTING
    override val description = "Listing"
    override fun parseAttachment(buffer: ByteBuffer, transactionVersion: Byte) = Attachment.DigitalGoodsListing(dp, buffer, transactionVersion)
    override fun parseAttachment(attachmentData: JsonObject) = Attachment.DigitalGoodsListing(dp, attachmentData)

    override suspend fun applyAttachment(transaction: Transaction, senderAccount: Account, recipientAccount: Account?) {
        val attachment = transaction.attachment as Attachment.DigitalGoodsListing
        dp.digitalGoodsStoreService.listGoods(transaction, attachment)
    }

    override suspend fun doValidateAttachment(transaction: Transaction) {
        val attachment = transaction.attachment as Attachment.DigitalGoodsListing
        if (attachment.name!!.isEmpty()
            || attachment.name.length > Constants.MAX_DGS_LISTING_NAME_LENGTH
            || attachment.description!!.length > Constants.MAX_DGS_LISTING_DESCRIPTION_LENGTH
            || attachment.tags!!.length > Constants.MAX_DGS_LISTING_TAGS_LENGTH
            || attachment.quantity < 0 || attachment.quantity > Constants.MAX_DGS_LISTING_QUANTITY
            || attachment.priceNQT <= 0 || attachment.priceNQT > Constants.MAX_BALANCE_NQT
        ) {
            throw BurstException.NotValidException("Invalid digital goods listing: " + attachment.jsonObject.toJsonString())
        }
    }

    override fun hasRecipient() = false
}
