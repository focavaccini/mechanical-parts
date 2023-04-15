//package br.com.inventory.mechanicalparts.Util;
//
//package com.smartbr.clinica.utils;
//
//import com.smartbr.clinica.exception.SmartRuntimeException;
//import com.smartbr.clinica.exception.enums.EnumSmartException;
//
//public class CnpjUtil {
//
//    private static final Integer CNPJ_DIGITS = 14;
//
//    public static boolean isValid(String value) {
//        if (StringUtil.isNullOrEmpty(value)) {
//            return false;
//        }
//
//        value = StringUtil.keepOnlyNumbers(value);
//
//        // fim do bloco.
//        if (value.length() == CNPJ_DIGITS) {
//            // Abaixo é verificado se o usuário digitou todos os digitos iguais,
//            // caso isto ocorra, é retornado um erro.
//            if ((value.compareTo("99999999999999") == 0)
//                    || (value.compareTo("00000000000000") == 0)
//                    || (value.compareTo("11111111111111") == 0)
//                    || (value.compareTo("22222222222222") == 0)
//                    || (value.compareTo("33333333333333") == 0)
//                    || (value.compareTo("44444444444444") == 0)
//                    || (value.compareTo("55555555555555") == 0)
//                    || (value.compareTo("66666666666666") == 0)
//                    || (value.compareTo("77777777777777") == 0)
//                    || (value.compareTo("88888888888888") == 0)) {
//                // se forem, atribui 1 ao teste e abaixo e exibida a mensagem de
//                // erro
//                return false;
//            }
//
//            // Inicia o cálculo do módulo do primeiro digito verificador do
//            // CNPJ
//            int DV1 = 0;
//            int PriNum = Integer.parseInt(value.substring(0, 1));
//            int SegNum = Integer.parseInt(value.substring(1, 2));
//            int TerNum = Integer.parseInt(value.substring(2, 3));
//            int QuaNum = Integer.parseInt(value.substring(3, 4));
//            int QuiNum = Integer.parseInt(value.substring(4, 5));
//            int SexNum = Integer.parseInt(value.substring(5, 6));
//            int SetNum = Integer.parseInt(value.substring(6, 7));
//            int OitNum = Integer.parseInt(value.substring(7, 8));
//            int NonNum = Integer.parseInt(value.substring(8, 9));
//            int DecNum = Integer.parseInt(value.substring(9, 10));
//            int DepNum = Integer.parseInt(value.substring(10, 11));
//            int DesNum = Integer.parseInt(value.substring(11, 12));
//            int Soma = (PriNum * 5) + (SegNum * 4) + (TerNum * 3)
//                    + (QuaNum * 2) + (QuiNum * 9) + (SexNum * 8)
//                    + (SetNum * 7) + (OitNum * 6) + (NonNum * 5)
//                    + (DecNum * 4) + (DepNum * 3) + (DesNum * 2);
//            int Resultado = 11 - (Soma % 11);
//
//            if ((Resultado == 10) || (Resultado == 11)) {
//                DV1 = 0;
//            } else {
//                DV1 = Resultado;
//            }
//
//            // Inicia o cálculo do módulo do segundo digito verificador do
//            // CNPJ
//            int DV2;
//            int PriNum2 = Integer.parseInt(value.substring(0, 1));
//            int SegNum2 = Integer.parseInt(value.substring(1, 2));
//            int TerNum2 = Integer.parseInt(value.substring(2, 3));
//            int QuaNum2 = Integer.parseInt(value.substring(3, 4));
//            int QuiNum2 = Integer.parseInt(value.substring(4, 5));
//            int SexNum2 = Integer.parseInt(value.substring(5, 6));
//            int SetNum2 = Integer.parseInt(value.substring(6, 7));
//            int OitNum2 = Integer.parseInt(value.substring(7, 8));
//            int NonNum2 = Integer.parseInt(value.substring(8, 9));
//            int DecNum2 = Integer.parseInt(value.substring(9, 10));
//            int DepNum2 = Integer.parseInt(value.substring(10, 11));
//            int DesNum2 = Integer.parseInt(value.substring(11, 12));
//            int Soma2 = (PriNum2 * 6) + (SegNum2 * 5) + (TerNum2 * 4)
//                    + (QuaNum2 * 3) + (QuiNum2 * 2) + (SexNum2 * 9)
//                    + (SetNum2 * 8) + (OitNum2 * 7) + (NonNum2 * 6)
//                    + (DecNum2 * 5) + (DepNum2 * 4) + (DesNum2 * 3)
//                    + (DV1 * 2);
//            int Resultado2 = 11 - (Soma2 % 11);
//
//            if ((Resultado2 == 10) || (Resultado2 == 11)) {
//                DV2 = 0;
//            } else {
//                DV2 = Resultado2;
//            }
//
//            // concatena o primeiro com o segundo digito e testa se eles
//            // estão corretos...
//            String VerDv = Integer.toString(DV1) + Integer.toString(DV2);
//
//            return VerDv.compareTo(value.substring(12, 14)) == 0;
//        }
//
//        return false;
//    }
//
//    public static boolean isCnpj(String value) {
//        if (StringUtil.isNotNullOrEmpty(value)) {
//            value = StringUtil.keepOnlyNumbers(value);
//            return value.length() == CNPJ_DIGITS;
//        }
//
//        return false;
//    }
//
//    public static void validate(String cnpj) {
//        if (!isValid(cnpj)) {
//            throw new SmartRuntimeException(EnumSmartException.CNPJ_INVALIDO, cnpj);
//        }
//    }
//
//    public static String format(String cpf) {
//        if (cpf == null) {
//            return cpf;
//        }
//
//        cpf = StringUtil.keepOnlyNumbers(cpf);
//
//        return Utils.formatter(cpf, "###.###.###-##");
//    }
//
//}