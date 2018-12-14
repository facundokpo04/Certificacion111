package Logica;

import Interfaces.RegistrarCampo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author facundo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        GestorCampos adminCampos = null;

        adminCampos = new GestorCampos("LaBea", "20340603193");

        adminCampos.registrarEstadoCampo("Creado", "el campo tiene un lote");
        adminCampos.registrarEstadoCampo("ParcialmenteTrabajado", "el Lote tiene un proyecto vigente");
        adminCampos.registrarEstadoCampo("Completamente Trabajado", "Todos los lotes tienen un proyecto de cultivo");
        adminCampos.registrarEstadoCampo("Desuso", "Ningun Lote tiene Proyecto cultivo");
        adminCampos.registrarTipoSuelo("Tipo I", "");
        adminCampos.registrarTipoSuelo("Tipo II", "");
        adminCampos.registrarTipoSuelo("Tipo III", "");
        adminCampos.registrarTipoSuelo("Tipo IV", "");
        adminCampos.registrarTipoSuelo("Tipo V", "");
        
        RegistrarCampo unPrinc = new RegistrarCampo(adminCampos);
        unPrinc.setVisible(true);

    }

}
