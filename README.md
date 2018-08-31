# doc-conversion-api

# Please check the file ConvertServiceImplTest to get example how to use this service.

I had published 2 api to integrate with https://docs.docconversionapi.com/
1. convert(ConvertRequestProperty convertRequestProperty) - Method: POST

- Argument param = ConvertRequestProperty class 

                {
                
                  inputFile : File datatype,
                  optionsJSON: String datatype,
                  viewerOptions: String datatype
               
                }
                
- Response: byte[] datatype

2.  status() - Method: GET
- Response: StatusResponse class

# Note : You need to provide applicationId and secretKey to create DocConversionManager(applicationID, secretKey) instance before use ConvertService.
