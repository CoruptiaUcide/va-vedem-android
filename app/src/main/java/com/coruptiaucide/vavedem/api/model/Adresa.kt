package com.coruptiaucide.vavedem.api.model

import com.google.gson.annotations.Expose

/**
 * Created by tiberiugolaes on 01/05/2017.
 */
internal class Adresa(@Expose val id: Int,
                      @Expose val codPostal: Int,
                      @Expose val localitatea: String,
                      @Expose val nr: Int,
                      @Expose val strada: String
)