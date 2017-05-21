package com.coruptiaucide.vavedem.api.database

import android.content.ContentValues
import android.database.Cursor
import com.coruptiaucide.vavedem.api.model.Adresa
import com.coruptiaucide.vavedem.api.model.Primarie

/**
 * Created by tiberiugolaes on 21/05/2017.
 */
internal object PrimariiDatabaseManager : DatabaseManager<Primarie>(TABLE_PRIMARII, Columns.array) {

    private object Columns {
        internal val id = Column("id", "INT NOT NULL PRIMARY KEY")
        internal val adresaId = Column("adresaId", "INT")
        internal val codPostal = Column("codPostal", "INT")
        internal val localitate = Column("localitate", "TEXT")
        internal val adresaNr = Column("adresaNr", "INT")
        internal val strada = Column("strada", "TEXT")
        internal val codFiscal = Column("codFiscal", "INT")
        internal val email = Column("email", "TEXT")
        internal val nume = Column("nume", "TEXT")
        internal val populatie = Column("populatie", "INT")
        internal val telefon = Column("telefon", "TEXT")

        internal val array: Array<Column> = arrayOf(id, adresaId, codPostal, localitate, adresaNr, strada, codFiscal, email, nume, populatie, telefon)
    }

    override fun generateCreateTableStatement(table: String, columns: Array<Column>): String {
        var query = "CREATE TABLE IF NOT EXISTS $table (${Columns.id.name} ${Columns.id.type}"
        for (column in columns) {
            query += ", ${column.name} ${column.type}"
        }
        return query + ")"
    }

    override fun toContentValues(t: Primarie, fk: Long?): ContentValues {
        val values = ContentValues()
        values.put(Columns.id.name, t.id)
        values.put(Columns.adresaId.name, t.adresa.id)
        values.put(Columns.codPostal.name, t.adresa.codPostal)
        values.put(Columns.localitate.name, t.adresa.localitatea)
        values.put(Columns.adresaNr.name, t.adresa.nr)
        values.put(Columns.strada.name, t.adresa.strada)
        values.put(Columns.codFiscal.name, t.codFiscal)
        values.put(Columns.email.name, t.email)
        values.put(Columns.nume.name, t.nume)
        values.put(Columns.populatie.name, t.populatie)
        values.put(Columns.telefon.name, t.telefon)
        return values
    }

    override fun get(cursor: Cursor) =
            Primarie(cursor.getInt(Columns.id.name),
                    Adresa(cursor.getInt(Columns.adresaId.name), cursor.getInt(Columns.codPostal.name), cursor.getString(Columns.localitate.name) ?: ""
                            , cursor.getInt(Columns.adresaNr.name), cursor.getString(Columns.strada.name) ?: ""),
                    cursor.getInt(Columns.codFiscal.name),
                    cursor.getString(Columns.email.name) ?: "",
                    cursor.getString(Columns.nume.name) ?: "",
                    cursor.getInt(Columns.populatie.name),
                    cursor.getString(Columns.telefon.name) ?: ""
            )

}