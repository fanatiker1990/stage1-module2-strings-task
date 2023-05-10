package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String accessModifier = null;
        String returnType = "";
        String methodName = "";
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(signatureString, "(");
        int counter = 1;
        while (stringTokenizer.hasMoreTokens()) {
            if (counter == 1) {
                String before = stringTokenizer.nextToken();
                StringTokenizer stringTokenizer1 = new StringTokenizer(before, " ");
                if (stringTokenizer1.countTokens() == 3) {
                    int counterInn = 1;
                    while (stringTokenizer1.hasMoreTokens()) {
                        if (counterInn == 1) {
                            accessModifier = stringTokenizer1.nextToken();
                        } else if (counterInn == 2) {
                            returnType = stringTokenizer1.nextToken();
                        } else if (counterInn == 3) {
                            methodName = stringTokenizer1.nextToken();
                        }
                        counterInn++;
                    }
                } else {
                    int counterInn = 1;
                    while (stringTokenizer1.hasMoreTokens()) {
                        if (counterInn == 1) {
                            returnType = stringTokenizer1.nextToken();
                        } else if (counterInn == 2) {
                            methodName = stringTokenizer1.nextToken();
                        }
                        counterInn++;
                    }
                }

            } else if (counter == 2) {
                String after = stringTokenizer.nextToken();
                if (after.equals(")")) {
                    break;
                }
                after = after.replace(")", "");
                StringTokenizer stringTokenizer1 = new StringTokenizer(after, " ");
                int counter1 = 1;
                String inn;
                String argType = "";
                String argName;
                int counterSig = 0;
                while (stringTokenizer1.hasMoreTokens()) {
                    inn = stringTokenizer1.nextToken();
                    if (counter1 % 2 == 0) {
                        argName = inn.replace(",", "");
                        MethodSignature.Argument argument = new MethodSignature.Argument(argType, argName);
                        arguments.add(counterSig, argument);
                        counterSig++;
                    } else {
                        argType = inn.replace(",", "");
                    }
                    counter1++;
                }
            }
            counter++;
        }

        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setReturnType(returnType);
        methodSignature.setMethodName(methodName);
        methodSignature.setAccessModifier(accessModifier);
        return methodSignature;


    }

}
