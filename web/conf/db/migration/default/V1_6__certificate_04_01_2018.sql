INSERT INTO public.message_template
(message_template_id, message_template_name, message_template_description, mail_template, sms_template)
VALUES('CERTICIATE', 'CERTICIATE', 'CERTICIATE',  XML('<?xml version="1.0" encoding="UTF-8"?>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
  <title>Certificate</title>

  <style>
    html {
      background-color: transparent;
    }

    body {
      background-color: white;
      border: 0px;
      margin: 0;
      padding: 15;
      text-align: center;
    }

    p {
      font-size: 10pt;
    }
  </style>
</head>

<body>
  <div style="margin-top:10px">
  </div>
  <img src="images/coat_of-arms.jpg" />
  <h1>FEDERAL REPUBLIC OF NIGERIA</h1>
  <h1>TEACHERS REGISTRATION COUNCIL OF NIGERIA</h1>
  <h6>Established By Act 31 of 1993</h6>

  <span style="margin-left:10px"></span>
  <h2>Certificate of Registration</h2>
  <h4>This is to certify that</h4>
  <h3>${surName} ${firstName} ${middleName}</h3>
  <hr style="max-width: 70%;" />
  <h4>With Registration No:</h4>
  <h3>${regNo}></h3>
  <hr style="max-width: 45%;" />
  <p>
    having fullfilled all the conditions provided in the Act has been duly registered by the Council as Certified Teacher
  </p>
  <p style="max-width:65%">
    <h4>Note that this temporary certificate is valid till: ${expiryDate}</h4>
  </p>

</body>

</html>
'), NULL);
