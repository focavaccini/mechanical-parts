//package br.com.inventory.mechanicalparts.Util;
//
////package com.smartbr.clinica.utils;
//
////import com.smartbr.clinica.exception.SmartRuntimeException;
////import com.smartbr.clinica.exception.enums.EnumSmartException;
//
//public class CpfUtil {
//
//    private static final Integer CPF_DIGITS = 11;
//
//    public static boolean isValid(String value) {
//        if (StringUtil.isNullOrEmpty(value)) {
//            return false;
//        }
//
//        value = StringUtil.keepOnlyNumbers(value);
//
//        // fim do bloco.
//        if (value.length() == CPF_DIGITS) {
//            // Verifica se os digitos informados são identicos...
//            if ((value.compareTo("99999999999") == 0)
//                    || (value.compareTo("00000000000") == 0)
//                    || (value.compareTo("11111111111") == 0)
//                    || (value.compareTo("22222222222") == 0)
//                    || (value.compareTo("33333333333") == 0)
//                    || (value.compareTo("44444444444") == 0)
//                    || (value.compareTo("55555555555") == 0)
//                    || (value.compareTo("66666666666") == 0)
//                    || (value.compareTo("77777777777") == 0)
//                    || (value.compareTo("88888888888") == 0)) {
//                // se forem, atribui 1 ao teste e abaixo e exibida a mensagem de
//                // erro
//                return false;
//            }
//
//            // Inicia o cálculo do módulo do primeiro digito verificador do
//            // CPF
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
//            int Soma = (PriNum * 10) + (SegNum * 9) + (TerNum * 8)
//                    + (QuaNum * 7) + (QuiNum * 6) + (SexNum * 5)
//                    + (SetNum * 4) + (OitNum * 3) + (NonNum * 2);
//            int Resultado = 11 - (Soma % 11);
//
//            if ((Resultado == 10) || (Resultado == 11)) {
//                DV1 = 0;
//            } else {
//                DV1 = Resultado;
//            }
//
//            // Inicia o cálculo do módulo do segundo digito verificador do
//            // CPF
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
//            int Soma2 = (PriNum2 * 11) + (SegNum2 * 10) + (TerNum2 * 9)
//                    + (QuaNum2 * 8) + (QuiNum2 * 7) + (SexNum2 * 6)
//                    + (SetNum2 * 5) + (OitNum2 * 4) + (NonNum2 * 3)
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
//            return VerDv.compareTo(value.substring(9, 11)) == 0;
//        }
//
//        return false;
//    }
//
//
//    public static boolean isCpf(String value) {
//        if (StringUtil.isNotNullOrEmpty(value)) {
//            value = StringUtil.keepOnlyNumbers(value);
//            return value.length() == CPF_DIGITS;
//        }
//
//        return false;
//    }
//
//    public static void validate(String cpf) {
//        if (!isValid(cpf)) {
//            throw new SmartRuntimeException(EnumSmartException.CPF_INVALIDO, cpf);
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