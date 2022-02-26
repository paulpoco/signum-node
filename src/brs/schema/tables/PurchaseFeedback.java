/*
 * This file is generated by jOOQ.
 */
package brs.schema.tables;


import brs.schema.Db;
import brs.schema.Indexes;
import brs.schema.Keys;
import brs.schema.tables.records.PurchaseFeedbackRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PurchaseFeedback extends TableImpl<PurchaseFeedbackRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>DB.purchase_feedback</code>
     */
    public static final PurchaseFeedback PURCHASE_FEEDBACK = new PurchaseFeedback();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PurchaseFeedbackRecord> getRecordType() {
        return PurchaseFeedbackRecord.class;
    }

    /**
     * The column <code>DB.purchase_feedback.db_id</code>.
     */
    public final TableField<PurchaseFeedbackRecord, Long> DB_ID = createField(DSL.name("db_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>DB.purchase_feedback.id</code>.
     */
    public final TableField<PurchaseFeedbackRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>DB.purchase_feedback.feedback_data</code>.
     */
    public final TableField<PurchaseFeedbackRecord, byte[]> FEEDBACK_DATA = createField(DSL.name("feedback_data"), SQLDataType.BLOB.nullable(false), this, "");

    /**
     * The column <code>DB.purchase_feedback.feedback_nonce</code>.
     */
    public final TableField<PurchaseFeedbackRecord, byte[]> FEEDBACK_NONCE = createField(DSL.name("feedback_nonce"), SQLDataType.VARBINARY(32).nullable(false), this, "");

    /**
     * The column <code>DB.purchase_feedback.height</code>.
     */
    public final TableField<PurchaseFeedbackRecord, Integer> HEIGHT = createField(DSL.name("height"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>DB.purchase_feedback.latest</code>.
     */
    public final TableField<PurchaseFeedbackRecord, Boolean> LATEST = createField(DSL.name("latest"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("1", SQLDataType.BOOLEAN)), this, "");

    private PurchaseFeedback(Name alias, Table<PurchaseFeedbackRecord> aliased) {
        this(alias, aliased, null);
    }

    private PurchaseFeedback(Name alias, Table<PurchaseFeedbackRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>DB.purchase_feedback</code> table reference
     */
    public PurchaseFeedback(String alias) {
        this(DSL.name(alias), PURCHASE_FEEDBACK);
    }

    /**
     * Create an aliased <code>DB.purchase_feedback</code> table reference
     */
    public PurchaseFeedback(Name alias) {
        this(alias, PURCHASE_FEEDBACK);
    }

    /**
     * Create a <code>DB.purchase_feedback</code> table reference
     */
    public PurchaseFeedback() {
        this(DSL.name("purchase_feedback"), null);
    }

    public <O extends Record> PurchaseFeedback(Table<O> child, ForeignKey<O, PurchaseFeedbackRecord> key) {
        super(child, key, PURCHASE_FEEDBACK);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Db.DB;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.PURCHASE_FEEDBACK_PURCHASE_FEEDBACK_ID_HEIGHT_IDX);
    }

    @Override
    public Identity<PurchaseFeedbackRecord, Long> getIdentity() {
        return (Identity<PurchaseFeedbackRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<PurchaseFeedbackRecord> getPrimaryKey() {
        return Keys.KEY_PURCHASE_FEEDBACK_PRIMARY;
    }

    @Override
    public PurchaseFeedback as(String alias) {
        return new PurchaseFeedback(DSL.name(alias), this);
    }

    @Override
    public PurchaseFeedback as(Name alias) {
        return new PurchaseFeedback(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PurchaseFeedback rename(String name) {
        return new PurchaseFeedback(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PurchaseFeedback rename(Name name) {
        return new PurchaseFeedback(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, Long, byte[], byte[], Integer, Boolean> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
