package com.methodia.android.testautomation.Model;

import org.parceler.Parcel;

/**
 * Created by Stefan.Doychev on 22.07.2015.
 */
@Parcel
@lombok.Getter
@lombok.Setter
@lombok.ToString
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor(suppressConstructorProperties = true)
public class ParcelerModel {
    String name;
    int id;
}