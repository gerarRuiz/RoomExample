package com.example.roomexample;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = EntidadBines.TABLE_NAME)
public class EntidadBines {

    public static final String TABLE_NAME = "CATALOGO_BINES";
    public static final String COLUMN_NAME = "ID";
    public static final String COLUMN_ID = BaseColumns._ID;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = EntidadBines.COLUMN_ID)
    public long id;

    @ColumnInfo(name = "cODIGO")
    private String cODIGO;

    @ColumnInfo(name = "tCPREFIJO")
    private int tCPREFIJO;

    @ColumnInfo(name = "tIPOFPGO")
    private int tIPOFPGO;

    @ColumnInfo(name = "sAIDFPGO")
    private int sAIDFPGO;

    @ColumnInfo(name = "tRIDFPGO")
    private String tRIDFPGO;

    @ColumnInfo(name = "tCDEBITO")
    private String tCDEBITO;

    @ColumnInfo(name = "fACTIVO")
    private int fACTIVO;

    @ColumnInfo(name = "tCTIPOCOM")
    private String tCTIPOCOM;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCODIGO() {
        return cODIGO;
    }

    public void setCODIGO(String cODIGO) {
        this.cODIGO = cODIGO;
    }

    public int getTCPREFIJO() {
        return tCPREFIJO;
    }

    public void setTCPREFIJO(int tCPREFIJO) {
        this.tCPREFIJO = tCPREFIJO;
    }

    public int getTIPOFPGO() {
        return tIPOFPGO;
    }

    public void setTIPOFPGO(int tIPOFPGO) {
        this.tIPOFPGO = tIPOFPGO;
    }

    public int getSAIDFPGO() {
        return sAIDFPGO;
    }

    public void setSAIDFPGO(int sAIDFPGO) {
        this.sAIDFPGO = sAIDFPGO;
    }

    public String getTRIDFPGO() {
        return tRIDFPGO;
    }

    public void setTRIDFPGO(String tRIDFPGO) {
        this.tRIDFPGO = tRIDFPGO;
    }

    public String getTCDEBITO() {
        return tCDEBITO;
    }

    public void setTCDEBITO(String tCDEBITO) {
        this.tCDEBITO = tCDEBITO;
    }

    public int getFACTIVO() {
        return fACTIVO;
    }

    public void setFACTIVO(int fACTIVO) {
        this.fACTIVO = fACTIVO;
    }

    public String getTCTIPOCOM() {
        return tCTIPOCOM;
    }

    public void setTCTIPOCOM(int tctipocom) {
        this.tCTIPOCOM = tCTIPOCOM;
    }

    public EntidadBines(String cODIGO, int tCPREFIJO, int tIPOFPGO, int sAIDFPGO, String tRIDFPGO, String tCDEBITO, int fACTIVO, String tCTIPOCOM) {
        this.cODIGO = cODIGO;
        this.tCPREFIJO = tCPREFIJO;
        this.tIPOFPGO = tIPOFPGO;
        this.sAIDFPGO = sAIDFPGO;
        this.tRIDFPGO = tRIDFPGO;
        this.tCDEBITO = tCDEBITO;
        this.fACTIVO = fACTIVO;
        this.tCTIPOCOM = tCTIPOCOM;
    }


}