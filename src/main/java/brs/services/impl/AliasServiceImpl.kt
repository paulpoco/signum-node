package brs.services.impl

import brs.Alias
import brs.Alias.Offer
import brs.Attachment
import brs.DependencyProvider
import brs.Transaction
import brs.db.BurstKey
import brs.db.VersionedEntityTable
import brs.services.AliasService

class AliasServiceImpl(dp: DependencyProvider) : AliasService {
    private val aliasStore = dp.aliasStore
    private val aliasTable: VersionedEntityTable<Alias>
    private val aliasDbKeyFactory: BurstKey.LongKeyFactory<Alias>
    private val offerTable: VersionedEntityTable<Offer>
    private val offerDbKeyFactory: BurstKey.LongKeyFactory<Offer>

    override suspend fun getAliasCount() = aliasTable.getCount().toLong()

    init {
        this.aliasTable = aliasStore.aliasTable
        this.aliasDbKeyFactory = aliasStore.aliasDbKeyFactory
        this.offerTable = aliasStore.offerTable
        this.offerDbKeyFactory = aliasStore.offerDbKeyFactory
    }

    override suspend fun getAlias(aliasName: String): Alias? {
        return aliasStore.getAlias(aliasName)
    }

    override suspend fun getAlias(aliasId: Long): Alias? {
        return aliasTable.get(aliasDbKeyFactory.newKey(aliasId))
    }

    override suspend fun getOffer(alias: Alias): Offer? {
        return offerTable.get(offerDbKeyFactory.newKey(alias.id))
    }

    override suspend fun getAliasesByOwner(accountId: Long, from: Int, to: Int): Collection<Alias> {
        return aliasStore.getAliasesByOwner(accountId, from, to)
    }

    override suspend fun addOrUpdateAlias(transaction: Transaction, attachment: Attachment.MessagingAliasAssignment) {
        var alias = getAlias(attachment.aliasName)
        if (alias == null) {
            val aliasDBId = aliasDbKeyFactory.newKey(transaction.id)
            alias = Alias(transaction.id, aliasDBId, transaction, attachment)
        } else {
            alias.accountId = transaction.senderId
            alias.aliasURI = attachment.aliasURI
            alias.timestamp = transaction.blockTimestamp
        }
        aliasTable.insert(alias)
    }

    override suspend fun sellAlias(transaction: Transaction, attachment: Attachment.MessagingAliasSell) {
        val aliasName = attachment.aliasName
        val priceNQT = attachment.priceNQT
        val buyerId = transaction.recipientId
        if (priceNQT > 0) {
            val alias = getAlias(aliasName)!!
            val offer = getOffer(alias)
            if (offer == null) {
                val dbKey = offerDbKeyFactory.newKey(alias.id)
                offerTable.insert(Offer(dbKey, alias.id, priceNQT, buyerId))
            } else {
                offer.priceNQT = priceNQT
                offer.buyerId = buyerId
                offerTable.insert(offer)
            }
        } else {
            changeOwner(buyerId, aliasName, transaction.blockTimestamp)
        }
    }

    override suspend fun changeOwner(newOwnerId: Long, aliasName: String, timestamp: Int) {
        val alias = getAlias(aliasName)!!
        alias.accountId = newOwnerId
        alias.timestamp = timestamp
        aliasTable.insert(alias)

        val offer = getOffer(alias)
        if (offer != null) {
            offerTable.delete(offer)
        }
    }
}
