package brs.db.sql

import brs.DependencyProvider
import brs.Order
import brs.db.BurstKey
import brs.db.VersionedEntityTable
import brs.db.store.OrderStore
import brs.schema.Tables.ASK_ORDER
import brs.schema.Tables.BID_ORDER
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.SortField

class SqlOrderStore(private val dp: DependencyProvider) : OrderStore {

    override val askOrderDbKeyFactory: DbKey.LongKeyFactory<Order.Ask> = object : DbKey.LongKeyFactory<Order.Ask>(ASK_ORDER.ID) {

        override fun newKey(ask: Order.Ask): BurstKey {
            return ask.dbKey
        }

    }
    override val askOrderTable: VersionedEntityTable<Order.Ask>
    override val bidOrderDbKeyFactory: DbKey.LongKeyFactory<Order.Bid> = object : DbKey.LongKeyFactory<Order.Bid>(BID_ORDER.ID) {

        override fun newKey(bid: Order.Bid): BurstKey {
            return bid.dbKey
        }

    }
    override val bidOrderTable: VersionedEntityTable<Order.Bid>

    init {
        askOrderTable = object : VersionedEntitySqlTable<Order.Ask>("ask_order", ASK_ORDER, askOrderDbKeyFactory, dp) {
            override fun load(ctx: DSLContext, record: Record): Order.Ask {
                return SqlAsk(record)
            }

            override suspend fun save(ctx: DSLContext, ask: Order.Ask) {
                saveAsk(ctx, ask)
            }

            override fun defaultSort(): Collection<SortField<*>> {
                return listOf(tableClass.field("creation_height", Int::class.java).desc())
            }
        }

        bidOrderTable = object : VersionedEntitySqlTable<Order.Bid>("bid_order", BID_ORDER, bidOrderDbKeyFactory, dp) {
            override fun load(ctx: DSLContext, record: Record): Order.Bid {
                return SqlBid(record)
            }

            override suspend fun save(ctx: DSLContext, bid: Order.Bid) {
                saveBid(ctx, bid)
            }

            override fun defaultSort(): Collection<SortField<*>> {
                return listOf(tableClass.field("creation_height", Int::class.java).desc())
            }
        }
    }

    override suspend fun getAskOrdersByAccountAsset(accountId: Long, assetId: Long, from: Int, to: Int): Collection<Order.Ask> {
        return askOrderTable.getManyBy(ASK_ORDER.ACCOUNT_ID.eq(accountId).and(ASK_ORDER.ASSET_ID.eq(assetId)), from, to)
    }

    override suspend fun getSortedAsks(assetId: Long, from: Int, to: Int): Collection<Order.Ask> {
        return askOrderTable.getManyBy(ASK_ORDER.ASSET_ID.eq(assetId), from, to, listOf(ASK_ORDER.field("price", Long::class.java).asc(), ASK_ORDER.field("creation_height", Int::class.java).asc(), ASK_ORDER.field("id", Long::class.java).asc()))
    }

    override suspend fun getNextOrder(assetId: Long): Order.Ask? {
        return dp.db.getUsingDslContext<Order.Ask?> { ctx ->
            val query = ctx.selectFrom(ASK_ORDER)
                    .where(ASK_ORDER.ASSET_ID.eq(assetId).and(ASK_ORDER.LATEST.isTrue))
                    .orderBy(ASK_ORDER.PRICE.asc(),
                            ASK_ORDER.CREATION_HEIGHT.asc(),
                            ASK_ORDER.ID.asc())
                    .limit(1)
                    .query
            val result = askOrderTable.getManyBy(ctx, query, true).iterator()
            if (result.hasNext()) result.next() else null
        }
    }

    override suspend fun getAll(from: Int, to: Int): Collection<Order.Ask> {
        return askOrderTable.getAll(from, to)
    }

    override suspend fun getAskOrdersByAccount(accountId: Long, from: Int, to: Int): Collection<Order.Ask> {
        return askOrderTable.getManyBy(ASK_ORDER.ACCOUNT_ID.eq(accountId), from, to)
    }

    override suspend fun getAskOrdersByAsset(assetId: Long, from: Int, to: Int): Collection<Order.Ask> {
        return askOrderTable.getManyBy(ASK_ORDER.ASSET_ID.eq(assetId), from, to)
    }

    private fun saveAsk(ctx: DSLContext, ask: Order.Ask) {
        ctx.mergeInto(ASK_ORDER, ASK_ORDER.ID, ASK_ORDER.ACCOUNT_ID, ASK_ORDER.ASSET_ID, ASK_ORDER.PRICE, ASK_ORDER.QUANTITY, ASK_ORDER.CREATION_HEIGHT, ASK_ORDER.HEIGHT, ASK_ORDER.LATEST)
                .key(ASK_ORDER.ID, ASK_ORDER.HEIGHT)
                .values(ask.id, ask.accountId, ask.assetId, ask.priceNQT, ask.quantityQNT, ask.height, dp.blockchain.height, true)
                .execute()
    }

    override suspend fun getBidOrdersByAccount(accountId: Long, from: Int, to: Int): Collection<Order.Bid> {
        return bidOrderTable.getManyBy(BID_ORDER.ACCOUNT_ID.eq(accountId), from, to)
    }

    override suspend fun getBidOrdersByAsset(assetId: Long, from: Int, to: Int): Collection<Order.Bid> {
        return bidOrderTable.getManyBy(BID_ORDER.ASSET_ID.eq(assetId), from, to)
    }

    override suspend fun getBidOrdersByAccountAsset(accountId: Long, assetId: Long, from: Int, to: Int): Collection<Order.Bid> {
        return bidOrderTable.getManyBy(
                BID_ORDER.ACCOUNT_ID.eq(accountId).and(
                        BID_ORDER.ASSET_ID.eq(assetId)
                ),
                from,
                to
        )
    }

    override suspend fun getSortedBids(assetId: Long, from: Int, to: Int): Collection<Order.Bid> {
        return bidOrderTable.getManyBy(BID_ORDER.ASSET_ID.eq(assetId), from, to, listOf(BID_ORDER.field("price", Long::class.java).desc(), BID_ORDER.field("creation_height", Int::class.java).asc(), BID_ORDER.field("id", Long::class.java).asc()))
    }

    override suspend fun getNextBid(assetId: Long): Order.Bid? {
        return dp.db.getUsingDslContext<Order.Bid?> { ctx ->
            val query = ctx.selectFrom(BID_ORDER)
                    .where(BID_ORDER.ASSET_ID.eq(assetId)
                            .and(BID_ORDER.LATEST.isTrue))
                    .orderBy(BID_ORDER.PRICE.desc(),
                            BID_ORDER.CREATION_HEIGHT.asc(),
                            BID_ORDER.ID.asc())
                    .limit(1)
                    .query
            val result = bidOrderTable.getManyBy(ctx, query, true).iterator()
            if (result.hasNext()) result.next() else null
        }
    }

    private fun saveBid(ctx: DSLContext, bid: Order.Bid) {
        ctx.mergeInto(BID_ORDER, BID_ORDER.ID, BID_ORDER.ACCOUNT_ID, BID_ORDER.ASSET_ID, BID_ORDER.PRICE, BID_ORDER.QUANTITY, BID_ORDER.CREATION_HEIGHT, BID_ORDER.HEIGHT, BID_ORDER.LATEST)
                .key(BID_ORDER.ID, BID_ORDER.HEIGHT)
                .values(bid.id, bid.accountId, bid.assetId, bid.priceNQT, bid.quantityQNT, bid.height, dp.blockchain.height, true)
                .execute()
    }

    internal inner class SqlAsk internal constructor(record: Record) : Order.Ask(record.get(ASK_ORDER.ID), record.get(ASK_ORDER.ACCOUNT_ID), record.get(ASK_ORDER.ASSET_ID), record.get(ASK_ORDER.PRICE), record.get(ASK_ORDER.CREATION_HEIGHT), record.get(ASK_ORDER.QUANTITY), askOrderDbKeyFactory.newKey(record.get(ASK_ORDER.ID)))

    internal inner class SqlBid internal constructor(record: Record) : Order.Bid(record.get(BID_ORDER.ID), record.get(BID_ORDER.ACCOUNT_ID), record.get(BID_ORDER.ASSET_ID), record.get(BID_ORDER.PRICE), record.get(BID_ORDER.CREATION_HEIGHT), record.get(BID_ORDER.QUANTITY), bidOrderDbKeyFactory.newKey(record.get(BID_ORDER.ID)))
}
