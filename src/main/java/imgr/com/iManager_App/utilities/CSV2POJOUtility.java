package imgr.com.iManager_App.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSV2POJOUtility
{
    @SuppressWarnings(value =
    { "rawtypes", "unchecked" })
    public static <T> List<T> getPOJOS4mCsv(String filePath, Class<T> pojoClass) throws Exception
    {
        List<T> pojos = null;
        if (StringUtils.hasText(filePath))
        {
            if (pojoClass != null)
            {
                File file = new File(filePath);
                if (file != null)
                {
                    FileInputStream fileStream;

                    try
                    {
                        fileStream = new FileInputStream(file);
                        Reader reader = new InputStreamReader(fileStream);
                        if (reader != null)
                        {
                            pojos = (List<T>) new CsvToBeanBuilder(reader).withSkipLines(1)
                                    .withType(pojoClass.getClass()).build().parse();
                        }

                    }

                    catch (Exception e)
                    {
                        // Try from Class Path instead
                        file = ResourceUtils.getFile("classpath:" + filePath);
                        if (file != null)
                        {
                            try
                            {
                                fileStream = new FileInputStream(file);
                                Reader reader = new InputStreamReader(fileStream);
                                if (reader != null)
                                {
                                    pojos = (List<T>) new CsvToBeanBuilder(reader).withSkipLines(1).withType(pojoClass)
                                            .build().parse();

                                }
                            }

                            catch (Exception e1)
                            {
                                throw e1;
                            }
                        }

                    }

                }

            }
        }

        return pojos;
    }

    public static <T> List<T> getPOJOS4mCsv(MultipartFile file, Class<T> pojoClass, boolean skipFirstLine,
            String dateFormat) throws Exception
    {
        List<T> pojos = null;
        if (!file.isEmpty())
        {
            if (pojoClass != null)
            {

                if (file.getInputStream() != null)
                {

                    BufferedReader fileReader = new BufferedReader(
                            new InputStreamReader(file.getInputStream(), "UTF-8"));

                    CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

                    Iterable<CSVRecord> csvRecords = csvParser.getRecords();

                    long cnt = 0;
                    pojos = new ArrayList<T>();
                    // Get the fields of the class object
                    Field[] fields = pojoClass.getDeclaredFields();
                    List<Method> methods = getSetters(pojoClass);
                    List<Field> fieldsList = Arrays.asList(fields);
                    if (CollectionUtils.isNotEmpty(methods) && CollectionUtils.isNotEmpty(fieldsList))
                    {
                        for (CSVRecord csvRecord : csvRecords)
                        {
                            cnt += 1;
                            if (skipFirstLine)
                            {
                                if (cnt > 1)
                                {
                                    setFlds(pojoClass, pojos, methods, fieldsList, csvRecord, dateFormat);
                                }
                            }
                            else
                            {
                                setFlds(pojoClass, pojos, methods, fieldsList, csvRecord, dateFormat);
                            }

                        }

                    }

                    csvParser.close();

                }

            }
        }

        return pojos;
    }

    public static List<Method> getSetters(Class<?> c)
    {
        List<Method> setters = new ArrayList<>();
        for (Method method : c.getDeclaredMethods())
        {
            if (method.getName().startsWith("set") && method.getParameterCount() == 1)
            {
                setters.add(method);
            }
        }
        return setters;
    }

    @SuppressWarnings(
    { "rawtypes", "unchecked" })
    public static <T> List<T> convertToModel(MultipartFile file, Class<T> responseType)
    {
        List<T> models;
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream())))
        {
            CsvToBean<?> csvToBean = new CsvToBeanBuilder(reader).withType(responseType)
                    .withIgnoreLeadingWhiteSpace(true).withIgnoreEmptyLine(true).withSkipLines(1).build();
            models = (List<T>) csvToBean.parse();
        }
        catch (Exception ex)
        {
            log.error("error parsing csv file {} ", ex);
            throw new IllegalArgumentException(ex.getCause().getMessage());
        }
        return models;
    }

    private static <T> void setFlds(Class<T> pojoClass, List<T> pojos, List<Method> methods, List<Field> fieldsList,
            CSVRecord csvRecord, String dateFormat) throws Exception
    {

        Object classObject = (T) pojoClass.newInstance();

        int fldCnt = 0;
        // Number of Columns must equal Setter Methods
        if (csvRecord.size() == methods.size())
        {
            for (Field field : fieldsList)
            {
                Method method = methods.stream().filter(m -> m.getName().toLowerCase().contains(field.getName()))
                        .findFirst().get();
                if (method != null)
                {
                    Object val = csvRecord.get(fldCnt);
                    if (field.getType().equals(Date.class))
                    {

                        Date valDate = UtilDurations.getDateFromStringandDateFormat(val.toString(), dateFormat);
                        method.invoke(classObject, valDate);

                    }
                    else
                    {
                        method.invoke(classObject, val);
                    }

                    fldCnt++;
                }
            }
        }

        pojos.add((T) classObject);
    }

}
