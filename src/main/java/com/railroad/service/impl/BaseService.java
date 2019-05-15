package com.railroad.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Stanislav Popovich
 */

public abstract class BaseService {


    /**
     * Returns date in necessary format
     * @param date - input date
     * @param dateFormat - necessary format
     * @return date
     */
    public Date getDate(String date, String dateFormat){
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Date result;
        try {
            result = format.parse(date);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Getting empty matrix
     * @param size size of matrix
     * @return array
     */
    public int[][] getEmptyMatrix(int size){
        int[][] matrix = new int[size][size];
        for(int row = 0; row< matrix.length; row++){
            for(int column = 0; column < matrix[row].length; column++){
                matrix[row][column] = 0;
            }
        }
        return matrix;
    }
}
