/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbb_reportes;

/**
 *
 * @author saulmestanza
 */
public class TipoDocumento {
	private static final Integer TIPO_CEDULA= 1;
	private static final Integer TIPO_RUC_NATURAL= 2;
	private static final Integer RUC_PRIVADA = 3;
	private static final Integer RUC_PUBLICA=4;
	
	public static Integer getTipoCedula() {
		return TIPO_CEDULA;
	}
	public static Integer getTipoRucNatural() {
		return TIPO_RUC_NATURAL;
	}
	public static Integer getRucPrivada() {
		return RUC_PRIVADA;
	}
	public static Integer getRucPublica() {
		return RUC_PUBLICA;
	}
	
	
}