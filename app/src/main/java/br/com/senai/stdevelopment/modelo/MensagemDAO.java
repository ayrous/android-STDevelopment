package br.com.senai.stdevelopment.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 22853582884 on 09/03/2018.
 */

public class MensagemDAO extends SQLiteOpenHelper{

    public MensagemDAO(Context context) {
        super(context, "Mensagens", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql ="CREATE TABLE mensagens(" +
                "id INTEGER PRIMARY KEY," +
                "mensagem TEXT" +
                ");";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS mensagens";
        sqLiteDatabase.execSQL(sql);

    }

    public void salvar(Mensagem mensagem){
        SQLiteDatabase dtb = getWritableDatabase();
        ContentValues dados = getContentValues(mensagem);
        dtb.insert("mensagens", null, dados);
    }

    @NonNull
    private ContentValues getContentValues(Mensagem mensagem){
        ContentValues dados = new ContentValues();

        dados.put("mensagem", mensagem.getMensagem());
        return  dados;
    }

    public List<Mensagem> buscaMensagens(){
        String sql = "SELECT * FROM mensagens";
        SQLiteDatabase dtb = getReadableDatabase();

        Cursor c = dtb.rawQuery(sql, null);
        List<Mensagem> mensagens = new ArrayList<Mensagem>();

        while (c.moveToNext()){
            Mensagem mensagem = new Mensagem();
            mensagem.setId(c.getLong(c.getColumnIndex("id")));
            mensagem.setMensagem(c.getString(c.getColumnIndex("mensagem")));
            mensagens.add(mensagem);
        }
        return  mensagens;
    }

    public void remover(Mensagem mensagem) {
        SQLiteDatabase dtb = getWritableDatabase();
        String[] parametros = {
                String.valueOf(mensagem.getId())
        };
        dtb.delete("mensagens", "id = ?", parametros);
    }

    public void editar(Mensagem mensagem){
        SQLiteDatabase dtb = getWritableDatabase();
        ContentValues dados = getContentValues(mensagem);
        String[] parametros = {
                mensagem.getId().toString()};
        dtb.update("mensagens", dados, "id = ?", parametros);
        }
    }