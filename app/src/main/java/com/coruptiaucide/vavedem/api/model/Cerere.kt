package com.coruptiaucide.vavedem.api.model

import com.google.gson.annotations.Expose
import java.util.*

/**
 * Created by tiberiugolaes on 01/05/2017.
 */
//Inca nu e definita dar am pus-o pentru a popula view-holderul din recyclerview-ul din Home Activity
internal class Cerere(@Expose val id: Int,
                      @Expose val primarie: Primarie, //s-ar putea sa schimbam cu id-ul primariei
                      @Expose val tip: String,
                      @Expose val data: Date,
                      @Expose val status: Int)