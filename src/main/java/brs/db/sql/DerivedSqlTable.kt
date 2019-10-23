package brs.db.sql

import brs.DependencyProvider
import brs.db.DerivedTable
import brs.util.logging.safeTrace
import org.jooq.Field
import org.jooq.impl.TableImpl
import org.slf4j.LoggerFactory

abstract class DerivedSqlTable internal constructor(internal val table: String, internal val tableClass: TableImpl<*>, private val dp: DependencyProvider) : DerivedTable {
    internal val heightField: Field<Int>
    internal val latestField: Field<Boolean>?

    init {
        logger.safeTrace { "Creating derived table for $table" }
        dp.derivedTableManager.registerDerivedTable(this)
        this.heightField = tableClass.field("height", Int::class.java)
        this.latestField = tableClass.field("latest", Boolean::class.java)
    }

    override suspend fun rollback(height: Int) {
        check(dp.db.isInTransaction()) { "Not in transaction" }
        dp.db.useDslContext { ctx -> ctx.delete(tableClass).where(heightField.gt(height)).execute() }
    }

    override suspend fun truncate() {
        check(dp.db.isInTransaction()) { "Not in transaction" }
        dp.db.useDslContext { ctx -> ctx.delete(tableClass).execute() }
    }

    override suspend fun trim(height: Int) {
        // Nothing to trim
    }

    override suspend fun finish() {
        // Nothing to finish
    }

    override suspend fun optimize() {
        dp.db.optimizeTable(table)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(DerivedSqlTable::class.java)
    }
}
