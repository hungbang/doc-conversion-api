# doc-conversion-api

# Please check the file ConvertServiceImplTest to get example how to use this service.

I had published 2 api to integrate with https://docs.docconversionapi.com/
1. convert(ConvertRequestProperty convertRequestProperty) - Method: POST

  - Argument param = ConvertRequestProperty class 

                {
                
                  inputFile : File,
                  optionsJSON: String,
                  viewerOptions: String
               
                }
                
  - Response: byte[] datatype

2.  status() - Method: GET
  - Response: StatusResponse class

# Note : 
You need to provide applicationId and secretKey before use ConvertService.

  - You can get the applicationId and secretKey from https://app.docconversionapi.com/#/createaccount

# How to use?

1. Generate applicationId and secretKey by create account at https://app.docconversionapi.com/#/createaccount

2. Download doc-conversion-api and build maven to get jar file.

3. Import jar file to your project.

4. Init ConvertService like :

        {
            final ConvertServer convertService = new ConvertServiceImpl("4608bdaf-d235-47fd-be88-19e887076faf", "6085b971-625b-48cc-acca-ff2e5fe0d9b2")
        }



5. See ConvertServiceImplTest to know how to call api method.

Enjoy! Everything comments from you guys will help me improve this source code and don't hesitate to create new pull request to improve this source code.
