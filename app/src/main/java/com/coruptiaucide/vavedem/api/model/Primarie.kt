package com.coruptiaucide.vavedem.api.model

import com.google.gson.annotations.Expose

/**
 * Created by tiberiugolaes on 01/05/2017.
 */
internal class Primarie(
        @Expose val id: Int,
        @Expose val adresa: Adresa,
        @Expose val codFiscal: Int,
        @Expose val email: String,
        @Expose val nume: String,
        @Expose val populatie: Int,
        @Expose val telefon: String)