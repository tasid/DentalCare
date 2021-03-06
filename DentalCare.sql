USE [master]
GO
/****** Object:  Database [DentalCare]    Script Date: 2/16/2015 8:19:58 PM ******/
CREATE DATABASE [DentalCare]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DentalCare', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\DentalCare.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'DentalCare_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\DentalCare_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [DentalCare] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DentalCare].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DentalCare] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DentalCare] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DentalCare] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DentalCare] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DentalCare] SET ARITHABORT OFF 
GO
ALTER DATABASE [DentalCare] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DentalCare] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DentalCare] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DentalCare] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DentalCare] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DentalCare] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DentalCare] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DentalCare] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DentalCare] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DentalCare] SET  DISABLE_BROKER 
GO
ALTER DATABASE [DentalCare] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DentalCare] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DentalCare] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DentalCare] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DentalCare] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DentalCare] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DentalCare] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DentalCare] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [DentalCare] SET  MULTI_USER 
GO
ALTER DATABASE [DentalCare] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DentalCare] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DentalCare] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DentalCare] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [DentalCare] SET DELAYED_DURABILITY = DISABLED 
GO
USE [DentalCare]
GO
/****** Object:  Table [dbo].[APPOINTMENT]    Script Date: 2/16/2015 8:19:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[APPOINTMENT](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[APPOINTMENTDATE] [datetime] NULL,
	[APPOINTMENTTIME] [datetime] NULL,
	[APPOINTMENTSTATUS] [int] NULL,
	[CREATEDAT] [datetime] NULL,
	[MODIFIEDAT] [datetime] NULL,
	[PROBLEMDESCRIPTION] [varchar](255) NULL,
	[REFERREDBY] [varchar](255) NULL,
 CONSTRAINT [PK__APPOINTM__3214EC27535BDB8D] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[jnd_appointment_doctor]    Script Date: 2/16/2015 8:19:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[jnd_appointment_doctor](
	[appointment_doctor_fk] [int] NOT NULL,
	[appointment_fk] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[appointment_doctor_fk] ASC,
	[appointment_fk] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[jnd_appointment_patient]    Script Date: 2/16/2015 8:19:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[jnd_appointment_patient](
	[appointment_fk] [int] NOT NULL,
	[appointment_patient_fk] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[appointment_fk] ASC,
	[appointment_patient_fk] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[jnd_prescription_doctor]    Script Date: 2/16/2015 8:19:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[jnd_prescription_doctor](
	[prescription_doctor_fk] [int] NOT NULL,
	[prescription_fk] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[prescription_doctor_fk] ASC,
	[prescription_fk] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[jnd_prescription_patient]    Script Date: 2/16/2015 8:19:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[jnd_prescription_patient](
	[prescription_patient_fk] [int] NOT NULL,
	[prescription_fk] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[prescription_patient_fk] ASC,
	[prescription_fk] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PERSON]    Script Date: 2/16/2015 8:19:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PERSON](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[UserType] [varchar](31) NULL,
	[ADDRESS] [varchar](255) NULL,
	[BLOODGROUP] [varchar](255) NULL,
	[CREATEDAT] [datetime] NULL,
	[DATEOFBIRTH] [datetime] NULL,
	[EMAIL] [varchar](255) NULL,
	[FIRSTNAME] [varchar](255) NULL,
	[GENDER] [varchar](255) NULL,
	[LASTNAME] [varchar](255) NULL,
	[MARITALSTATUS] [varchar](255) NULL,
	[MODIFIEDAT] [datetime] NULL,
	[PASSWORD] [varchar](255) NULL,
	[PHONENUMBER] [varchar](255) NULL,
	[USERROLE] [varchar](255) NULL,
	[DEPARTMENT] [varchar](255) NULL,
	[JOININGDATE] [datetime] NULL,
	[POSITION] [varchar](255) NULL,
	[PROFILEDESCRIPTION] [text] NULL,
	[STATUSDESCRIPTION] [varchar](255) NULL,
	[WORKSTATUS] [varchar](255) NULL,
	[DISABILITY] [varchar](255) NULL,
	[IMAGEURL] [varchar](250) NULL,
	[DEGREE] [varchar](50) NULL,
	[IMAGEURLPROFILEDETAIL] [varchar](250) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PRESCRIPTION]    Script Date: 2/16/2015 8:19:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PRESCRIPTION](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[CREATEDAT] [datetime] NULL,
	[MEDICATION] [varchar](255) NULL,
	[MODIFIEDAT] [datetime] NULL,
	[SYMPTOMS] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[PERSON] ON 

INSERT [dbo].[PERSON] ([ID], [UserType], [ADDRESS], [BLOODGROUP], [CREATEDAT], [DATEOFBIRTH], [EMAIL], [FIRSTNAME], [GENDER], [LASTNAME], [MARITALSTATUS], [MODIFIEDAT], [PASSWORD], [PHONENUMBER], [USERROLE], [DEPARTMENT], [JOININGDATE], [POSITION], [PROFILEDESCRIPTION], [STATUSDESCRIPTION], [WORKSTATUS], [DISABILITY], [IMAGEURL], [DEGREE], [IMAGEURLPROFILEDETAIL]) VALUES (1, N'D', N'1000 N 4TH ST, Fairfield, IA 52556', N'O+', NULL, CAST(N'1976-01-07 00:00:00.000' AS DateTime), N'mhossain@gmail.com', N'Dr. Shyama Prosad', N'Male', N'Mitra', N'Married', CAST(N'2015-02-13 01:06:40.853' AS DateTime), N'mhossain123', N'641-253-5261', N'Doctor', NULL, CAST(N'2013-02-13 00:00:00.000' AS DateTime), NULL, N'Dr Shyama Prosad Mitra graduated from Rajshahi Medical College in 1995. He obtained his DA from Bangabandhu Sheikh Mujib Medical University (BSMMU) in 1999 and FCPS from Bangladesh College of Physicians and Surgeons in 2004. His career in Anaesthesia started in BIRDEM where he gathered substantial experience in managing patients with endocrine disorders. Later in BSMMU he was trained by another group of experienced trainers in developing skill in all branches of Anaesthesia. Before joining Apollo Hospitals Dhaka in 2005 he was working as a consultant in Anaesthesia and intensive care in Dhaka Medical College Hospital, Dhaka.
During his training period he developed interest in regional anesthesia and still pursuing the same in Apollo Hospitals Dhaka.In his career after being a fellow of BCPS he was given the opportunity to take part in clinical training of DA, MD and FCPS students in BSMMU and training of EOC and resident trainees in Dhaka Medical College Hospital.
Products of his research works have been published in various journals of home and abroad and he has presented papers in a many symposia a good number of which had been in SAARC countries’ regional seminars. After joining AHD he received training in Advanced Anaesthesia techniques in Chennai India
He maintains academic touch and remains actively involved in academic activities of the department of Anaesthesiology of Apollo Hospitals Dhaka.His special interest is regional Anaesthesia, Pain Management and Anaesthesia of critically ill patients.', N'Working', N'Active', NULL, N'resources/image/profile/ShymaPrasadMitra.jpg', N'MBBS, FCPS', N'resources/image/profile/view/ShymaPrasadMitra.jpg')
INSERT [dbo].[PERSON] ([ID], [UserType], [ADDRESS], [BLOODGROUP], [CREATEDAT], [DATEOFBIRTH], [EMAIL], [FIRSTNAME], [GENDER], [LASTNAME], [MARITALSTATUS], [MODIFIEDAT], [PASSWORD], [PHONENUMBER], [USERROLE], [DEPARTMENT], [JOININGDATE], [POSITION], [PROFILEDESCRIPTION], [STATUSDESCRIPTION], [WORKSTATUS], [DISABILITY], [IMAGEURL], [DEGREE], [IMAGEURLPROFILEDETAIL]) VALUES (2, N'D', N'1000 N 4TH ST, Fairfield, IA 52557', N'A+', NULL, NULL, N'abhuiyan@dcare.com', N'Dr. Alim Akther', N'Male', N'Bhuiyan', N'Married', CAST(N'2015-02-09 01:05:45.363' AS DateTime), N'abhuiyan123', N'641-521-2536', N'Doctor', NULL, NULL, NULL, N'Dr. Alim Akther Bhuiyan is the only American Board Certified Neurologist and Epilepsy Specialist in the country. He graduated from Chittagong Medical College in 1988. Later, he obtained his Diploma in Tropical Medicine and Hygiene (DTM&H) from London School of Tropical Medicine and Hygiene. Dr. Bhuiyan has done post-graduate residency training in Internal Medicine from Queens Hospital Center (affiliated with Mount Sinai Hospital) under City University of New York, USA .
He did post-graduate residency training in Neurology from State University of New York, Syracuse, USA. During this period, he was awarded as the best Resident for teaching in his department for two consecutive years.

He has also done his Post- Doctoral Fellowship in Epilepsy and Clinical Neuro-Physiology from the same University in USA. He obtained coveted Board Certification in Neurology from American Board of Psychiatry and Neurology in 1999.

He has more than 14 years experience, and has worked in different capacities. He has worked as a Consultant Neurologist at Veterans Administration (VA) Hospital in Syracuse, New York, USA. He served as Associate Professor & Head of the Department of Neurology at Bangladesh Medical College, Dhaka, before joining Apollo Hospitals Dhaka on 21 February 2005. He has  to his credit multiple national and international publications in Neurology.', N'Working', N'Active', NULL, N'resources/image/profile/AlimAkhterBhuiyan.jpg', N'MBBS, DTM N H - UK, MD - USA', N'resources/image/profile/view/AlimAkhterBhuiyan.jpg')
INSERT [dbo].[PERSON] ([ID], [UserType], [ADDRESS], [BLOODGROUP], [CREATEDAT], [DATEOFBIRTH], [EMAIL], [FIRSTNAME], [GENDER], [LASTNAME], [MARITALSTATUS], [MODIFIEDAT], [PASSWORD], [PHONENUMBER], [USERROLE], [DEPARTMENT], [JOININGDATE], [POSITION], [PROFILEDESCRIPTION], [STATUSDESCRIPTION], [WORKSTATUS], [DISABILITY], [IMAGEURL], [DEGREE], [IMAGEURLPROFILEDETAIL]) VALUES (3, N'D', N'Kalkata, India', N'A+', NULL, NULL, N'cpdokwal@dcare.com', N'Dr. Chandra Prakash', N'Male', N'Dokwal', N'Married', CAST(N'2015-02-09 01:07:25.833' AS DateTime), N'cpdokwal123', N'641-253-5261', N'Doctor', NULL, NULL, NULL, N'Dr. Chandra Prakash Dokwal graduated from Seth GS Medical College & KEM Hospital, Mumbai, India in 1978, and did his post-graduation training in Internal & Respiratory Medicine for 3 years from the same institute. KEM Hospital is one of the largest, renowned, 1500-bedded, public teaching hospital in Bombay. Dr. Dokwal received MD in Chest Medicine from Bombay University in 1982. Next, he worked at Bombay Hospital, which is the largest private hospital in Bombay. He then joined BM Birla Heart Research Centre, Kolkata in 1989 as Consultant Respiratory Physician & Bronchoscopist, and worked there up to 2000. During this period, he was also attached to Calcutta Medical Research Institute (CMRI). Later, he moved to Mumbai, and joined Guru Nanak Hospital, Mumbai in 2000. He was also attached to Hira Mongi Navneet Hospital and Jupiter Hospital in Thane, Mumbai before joining Apollo Hospital Dhaka.

Dr. Dokwal has expertise in Fibreoptic Bronchoscopy. He has performed more than 3,000 Bronchoscopies for both diagnostic and therapeutic purposes in the past 15 years. He has also performed various invasive pulmonary procedures. In addition, he has special interest in Critical Care Medicine and Ventilators. Dr. Dokwal has written many articles in medical journals. He has also written a medical book for students and physicians.', N'On Leave', N'Inactive', NULL, N'resources/image/profile/ChandraPrakasDhwakal.jpg', N'MBBS, MD', N'resources/image/profile/view/ChandraPrakasDhwakal.jpg')
INSERT [dbo].[PERSON] ([ID], [UserType], [ADDRESS], [BLOODGROUP], [CREATEDAT], [DATEOFBIRTH], [EMAIL], [FIRSTNAME], [GENDER], [LASTNAME], [MARITALSTATUS], [MODIFIEDAT], [PASSWORD], [PHONENUMBER], [USERROLE], [DEPARTMENT], [JOININGDATE], [POSITION], [PROFILEDESCRIPTION], [STATUSDESCRIPTION], [WORKSTATUS], [DISABILITY], [IMAGEURL], [DEGREE], [IMAGEURLPROFILEDETAIL]) VALUES (4, N'D', N'Dhaka, Bangladesh', N'A+', NULL, NULL, N'buahmad@dcare.com', N'Borhan Uddin', N'Male', N'Ahmad', N'Married', CAST(N'2015-02-09 02:01:23.097' AS DateTime), N'buahmad123', N'641-521-2537', N'Doctor', NULL, NULL, NULL, N'Dr. Borhan Uddin Ahmad, has joined as Senior Consultant in the Department of Internal Medicine.
Dr. Borhan Uddin Ahmad completed his graduation from Dhaka Medical College in January 1984. He went to United Kingdom in 1988 and passed PLAB test to get his GMC registration to work in the NHS. As a junior doctor he started his training in Internal Medicine in the UK and also worked as a Registrar at Rotherham General Hospital, South Yorkshire, UK. He worked in different hospitals in England for more than 5 years and completed his MRCP (UK) in 1995 from Royal College of Physicians of United Kingdom.

On his return to Bangladesh he joined Bangladesh Medical College & Hospital in November 1995 as Assistant Professor of Medicine and worked there till 2000. He was recruited by one of the reputed hospital in Saudi Arabia in 2000 where he worked as a Consultant in Emergency Medicine. Dr. Borhan is registered with Saudi Commission for Health Specialties as Consultant Internal Medicine.

From 2006 to 2011, he worked as a Senior Consultant in the Department of Emergency Medicine in Apollo Hospital Dhaka and after a short break of few months working abroad he has rejoined here again as Senior Consultant, Internal Medicine to contribute with his experience in the field of Internal Medicine.', N'Working', N'Inactive', NULL, N'resources/image/profile/BorhanUddin.jpg', N'MBBS, MD', N'resources/image/profile/view/BorhanUddin.jpg')
INSERT [dbo].[PERSON] ([ID], [UserType], [ADDRESS], [BLOODGROUP], [CREATEDAT], [DATEOFBIRTH], [EMAIL], [FIRSTNAME], [GENDER], [LASTNAME], [MARITALSTATUS], [MODIFIEDAT], [PASSWORD], [PHONENUMBER], [USERROLE], [DEPARTMENT], [JOININGDATE], [POSITION], [PROFILEDESCRIPTION], [STATUSDESCRIPTION], [WORKSTATUS], [DISABILITY], [IMAGEURL], [DEGREE], [IMAGEURLPROFILEDETAIL]) VALUES (5, N'D', N'Madraz, India', N'AB+', NULL, NULL, N'mali@dcare.com', N'Dr. Mohahmmad', N'Male', N'Ali', N'Married', CAST(N'2015-02-09 01:10:09.157' AS DateTime), N'mali123', N'641-253-6152', N'Doctor', NULL, NULL, NULL, N'Dr. M. Ali completed his MBBS from Dhaka University in 1984 and MS (Orthopaedics) from the same university in 1997. Afterwards, did Fellowship in Sports Medicine from Post-Graduate Medical Institute, Singapore. Dr. M Ali has performed more than 1,000 arthroscopic reconstructive surgeries and about 100 total knee replacements, along with other trauma and orthopaedic surgeries including sports injury and arthritis, i.e. age related wear and tear. He has special interest in arthoplasty and arthroscopic knee surgery, and he is considered the only knee surgeon of the country. Further, he is trained in autologous cartilage implant of joints. He is passionate about orthpaedic rehabilitation of physically disabled children.

Before joining Apollo Hospitals Dhaka, Dr. M. Ali was working at NITOR (Pangu Hospital), Dhaka as Assistant Professor.', N'On Leave', N'Inactive', NULL, N'resources/image/profile/MohammadAli.jpg', N'MBBS, MS, Fellow', N'resources/image/profile/view/MohammadAli.jpg')
INSERT [dbo].[PERSON] ([ID], [UserType], [ADDRESS], [BLOODGROUP], [CREATEDAT], [DATEOFBIRTH], [EMAIL], [FIRSTNAME], [GENDER], [LASTNAME], [MARITALSTATUS], [MODIFIEDAT], [PASSWORD], [PHONENUMBER], [USERROLE], [DEPARTMENT], [JOININGDATE], [POSITION], [PROFILEDESCRIPTION], [STATUSDESCRIPTION], [WORKSTATUS], [DISABILITY], [IMAGEURL], [DEGREE], [IMAGEURLPROFILEDETAIL]) VALUES (6, N'D', N'London, UK', N'A-', NULL, NULL, N'jmanzoor@dcare.com', N'Jasmin', N'Female', N'Manzoor', N'Married', CAST(N'2015-02-09 02:00:53.300' AS DateTime), N'jmanzoor123', N'641-523-2563', N'Doctor', NULL, NULL, NULL, N'Dr. Jasmin Manzoor graduated from Bangladesh and after graduation she pursued her higher education in UK and USA. She did Diploma in Dermatological Science from University of Wales College of Medicine, Cardiff, UK. In this course, her teachers were Dr. Ronald Marks, Dr. Long, Dr. Knight, and Dr. Finlay; who are world-renowned Dermatologists and authors of Dermatology textbooks.

After this, she did Masters in Dermatological Science from Boston University School of Medicine, Boston, USA. During her Masters, she was working in Boston Uiversity Medical Center under the supervision of Dr. Gilchrist, Dr. Amal K. Kurban, Dr. Tania Phillips, Dr. Jag Bhawan and several other workd renowned Dermatologists.
She passed American Medical Licensing Board and then joined back to Boston University School of Medicine for post-doctoral Clinical Fellowship in Advanced Dermatology & Wound Healing, under supervision of Dr. Tania Phillips. During her fellowship with her clinical duties, she worked extensively on cryopreserved skin graft with collaboration of Harvard Medical Center and Boston University Medical Center under the supervision of Professor Dr. Howard Green of Harvard Medical Center. She extensively worked on apligraft (world’s first bioengineered skin which is equivalent to normal skin), ageing process and anti-wrinkle medicines. She was involved with wound healing clinic of Boston University Medical Center, where she treated patients and did many wound healing studies.She attended and presented many papers in several international conferences, She has several publications in international reputed journals. She has wide expertise in Adult Dermatology, Paediatric Dermatology, Wound Healing, Dermatological Surgery, Cosmetic Dermatology, Laser Surgery, Dermoscopy, and Dermato-pathology for more than 15 years. She has wide expertise on white skin problems and skin cancers.After she came back from USA with the dream of serving her own country with advanced knowledge in Dermatology, she joined as Assistant Professor of Dermatology in the Department of Dermatology at Bangladesh Medical College & Hospital, Dhaka in 2000. After joining Apollo Hospitals Dhaka in 2005, she has served more than 38,000 out-patients and in-patients, and has carried out more than 10,000 Dermatologic, Laser, and Cosmetic Surgeries. Every year she attends international conferences to update her knowledge.', N'Working', N'Active', NULL, N'resources/image/profile/JasminManzoor.jpg', N'MBBS, FCPS', N'resources/image/profile/view/JasminManzoor.jpg')
INSERT [dbo].[PERSON] ([ID], [UserType], [ADDRESS], [BLOODGROUP], [CREATEDAT], [DATEOFBIRTH], [EMAIL], [FIRSTNAME], [GENDER], [LASTNAME], [MARITALSTATUS], [MODIFIEDAT], [PASSWORD], [PHONENUMBER], [USERROLE], [DEPARTMENT], [JOININGDATE], [POSITION], [PROFILEDESCRIPTION], [STATUSDESCRIPTION], [WORKSTATUS], [DISABILITY], [IMAGEURL], [DEGREE], [IMAGEURLPROFILEDETAIL]) VALUES (7, N'D', N'Fairfield, IA 52556', N'A+', NULL, NULL, N'mhransary@dcare.com', N'Md. Hafizur Rahman', N'Male', N'Ansary', N'Married', CAST(N'2015-02-09 01:08:40.073' AS DateTime), N'mhransary123', N'641-856-5642', N'Doctor', NULL, NULL, NULL, N'Dr. Md. Hafizur Rahman Ansary graduated from Mymensingh Medical College in 1980 and did his DMRT in Radiation Oncology in 1990 from IPGMR Dhaka under Dhaka University. Dr. Ansary has served as Director and Professor at National Institute of Cancer Research & Hospital (NICRH) from August 2009 and to February 2010. After NCIRH, he joined as Professor, Radiation Oncology at Dhaka Medical College & Hospital. He has worked in the Department of Radiotherapy and Radiation Oncology in institutes like Dhaka Medical College & Hospital, National Institute of Cancer Research and Hospital, Chittagong Medical College, Sylhet Osmani Medical College, and Sher-e-Bangla Medical College in different capacities in the last 20 years.
Dr. Ansary has a rich experience in the latest Radiotherapy modalities and in the management of the latest Radiotherapy equipment. He has received intensive training in cancer patient management from different foreign institutions like Tata Memorial Hospital, Mumbai, India; National Cancer Institute, Singapore; MD Anderson Cancer Center, Texas, USA; etc. Dr. Ansary also has many national and international publications and paper presentations to his credit. He has participated in many national and international scientific conference and CME programs.', N'Working', N'Active', NULL, N'resources/image/profile/HafizurRahman.jpg', N'MBBS, DMRT', N'resources/image/profile/view/HafizurRahman.jpg')
INSERT [dbo].[PERSON] ([ID], [UserType], [ADDRESS], [BLOODGROUP], [CREATEDAT], [DATEOFBIRTH], [EMAIL], [FIRSTNAME], [GENDER], [LASTNAME], [MARITALSTATUS], [MODIFIEDAT], [PASSWORD], [PHONENUMBER], [USERROLE], [DEPARTMENT], [JOININGDATE], [POSITION], [PROFILEDESCRIPTION], [STATUSDESCRIPTION], [WORKSTATUS], [DISABILITY], [IMAGEURL], [DEGREE], [IMAGEURLPROFILEDETAIL]) VALUES (8, N'D', N'Fairfield, IA 52556', N'O-', NULL, NULL, N'safroza@dcare.com', N'Shahnaz', N'Female', N'Afroza', N'Single', CAST(N'2015-02-09 02:01:04.443' AS DateTime), N'safroza123', N'641-865-5642', N'Doctor', NULL, NULL, NULL, N'Dr. Shahnaz Afroza graduated from MymensinghMedicalCollegeHospital in 1995. After basic medical education she started her career in Anaesthesiology as Resident in Bangabandhu Sheikh Mujib Medical University (BSMMU) in the department of Anaesthesiology, Pain,Palliative & Intensive Care unit. In 2004, she completed her FCPS in Anaesthesiology. Then she joined as Registrar of Anesthesiology & ICU in Apollo Hospitals Dhaka (AHD). She was promoted as Senior Registerar in 2006. In her service period, she sent to NeuroScienceCenter, AIIMS New Delhi for super-speciality training in Neuro-anesthesia & NICU. Dr. Afroza was promoted as Specialist in 2010. After working more than 7 years, she left AHD for her academic interest and joined as Assistant Professor at Anaesthesiology & ICU Department in BangladeshMedicalCollege and Hospital, Uttara, Dhaka.

During her 15 years working experience, she has keen interest in Intensive Care Medicine. She set up few Private Intensive Care units in Dhaka and also conducted training to ICU. In addition to her interest in Neuro-anaesthesia, she has been involved in Intensive Care Unit. She was associated with different academic activities with undergraduate and post graduate medical students.

Dr. Shahnaz attended and presented papers in several national and international conferences. Also, she has multiple national and international publications to her credit.', N'Working', N'Active', NULL, N'resources/image/profile/ShahnazAfroza.jpg', N'MBBS, FCPS', N'resources/image/profile/view/ShahnazAfroza.jpg')
INSERT [dbo].[PERSON] ([ID], [UserType], [ADDRESS], [BLOODGROUP], [CREATEDAT], [DATEOFBIRTH], [EMAIL], [FIRSTNAME], [GENDER], [LASTNAME], [MARITALSTATUS], [MODIFIEDAT], [PASSWORD], [PHONENUMBER], [USERROLE], [DEPARTMENT], [JOININGDATE], [POSITION], [PROFILEDESCRIPTION], [STATUSDESCRIPTION], [WORKSTATUS], [DISABILITY], [IMAGEURL], [DEGREE], [IMAGEURLPROFILEDETAIL]) VALUES (9, N'D', N'1000 N 4TH ST, Fairfield, IA 52556', N'B+', NULL, NULL, N'miqbal@dcare.com', N'Prof. Dr. Kazi', N'Male', N'Mesbahuddin lqbal', N'Married', CAST(N'2015-02-09 01:09:32.107' AS DateTime), N'miqbal123', N'641-521-5362', N'Doctor', NULL, NULL, NULL, N'Prof. (Dr.) Kazi Mesbahuddin lqbal graduated from Dhaka Medical College in early 1969. He moved to UK in 1972 for Post Graduate training and qualifications. During his approximate ten year stay at UK, he obtained DA from London, FFARCS from the Royal College of Surgeons of Ireland, and FRCA from the Royal College of Surgeons (now Anaesthetists) of England. His training included active exposure to all branches of Anaesthesia as per the requirement of Royal College. He had the opportunity to work with the likes of Prof. Thornton of Sheffield University, Prof. Utting of Liverpool University and Dr. Jackson Rees of Myrtle Street Hospital in Liverpool. Dr. Julian Leigh of Surrey University was his clinical tutor while working in Royal Surrey Hospital and Surrey University.

He was awarded FCPS in 1997 from Bangladesh College of Physician and Surgeon ( BCPS). He started as Associate Professor in the then IPGMR (now Bangabahdhu Sheikh Mujib Medical University) in 1982 and became Professor of Anaesthesia in Chittagong Medical College in 1989. Then from 1991 to 2005, he worked as Chairman of the Department of Anaesthesia, Analgesia and Intensive care Medicine of BSMMU (formerly IPGMR). He voluntarily retired from the University to join BIRDEM in September, 2005 and later in June, 2006 he started to work at Apollo Hospitals Dhaka.

He is a member of Bangladesh Society of Anaesthesiologist (BSA) and he acted as Chairman, Scientific Committee for long 23 years. He was the founder Editor of the BSA Journal. He is also an active member of BSSP, Bangladesh Chapter of IASP. He is the former President and the current Editor-in-Chief of the Journal of the society. He was the Chairman of the Editorial Board of the Journal of BCPS, and currently an overseas member of the editorial board of “Critical Care and Shock”, published jointly by the Philippine Society of Critical Care Medicine, Society of Intensive Care Medicine (Singapore), Korean Society of Critical Care Medicine, and Chinese Society of Critical Care Medicine.

He is an examiner of FCPS, MD and DA in Bangladesh and he had been an overseas examiner of FCPS in Pakistan and DA in Nepal. He acted as invited speaker on various topics in Anaesthesia in Thailand, Japan, Pakistan, Indonesia, Srilanka, India, South Korea, Singapore, and presented research papers in conferences in theses countries and USA.

He has at least 60 publications in national and international journals. He has written a chapter entitled “Methods of Drug Administration” in a General Pharmacology textbook edited by Misbahuddin and Islam, and published by Books and Allied (Pvt.) Ltd. India.', N'Working', N'Active', NULL, N'resources/image/profile/KaziMesbahuddinIqbal.jpg', N'MBBS, DA, FFARCS, FRCA, FCPS', N'resources/image/profile/view/KaziMesbahuddinIqbal.jpg')
INSERT [dbo].[PERSON] ([ID], [UserType], [ADDRESS], [BLOODGROUP], [CREATEDAT], [DATEOFBIRTH], [EMAIL], [FIRSTNAME], [GENDER], [LASTNAME], [MARITALSTATUS], [MODIFIEDAT], [PASSWORD], [PHONENUMBER], [USERROLE], [DEPARTMENT], [JOININGDATE], [POSITION], [PROFILEDESCRIPTION], [STATUSDESCRIPTION], [WORKSTATUS], [DISABILITY], [IMAGEURL], [DEGREE], [IMAGEURLPROFILEDETAIL]) VALUES (10, N'D', N'Fairfield, IA 52557', N'O+', NULL, NULL, N'gara@gmail.com', N'Dr. Gulshan', N'Female', N'Ara', N'Married', CAST(N'2015-02-09 02:01:14.340' AS DateTime), N'gara0122', N'641-521-5263', N'Doctor', NULL, NULL, NULL, N'Dr. Gulshan Ara graduated from Dhaka University in 1985 and did her MS in Obstetrics/Gynaecology from the same institute. She then completed MCPS and FCPS from Bangladesh College of Physicians & Surgeons (BCPS), Dhaka. She has over 22 years of experience in the field of Obstetrics/Gynaecology. She is trained in advanced laparoscopic surgery from Kerala and Chennai, India. Dr. Gulshan Ara has developed her skills in modern Obstetrics/Gynaecology and Infertility Management. Before joining Apollo Hospitals Dhaka, she served as Consultant in the Department of Obstetrics/Gynaecology at Dhaka Medical College & Hospital for seven years.', N'Working', N'Active', NULL, N'resources/image/profile/GulshanAra.jpg', N'MBBS, MCPS, MS', N'resources/image/profile/view/GulshanAra.jpg')
SET IDENTITY_INSERT [dbo].[PERSON] OFF
ALTER TABLE [dbo].[jnd_appointment_doctor]  WITH CHECK ADD  CONSTRAINT [jndppntmnppntmntdctrfk] FOREIGN KEY([appointment_doctor_fk])
REFERENCES [dbo].[PERSON] ([ID])
GO
ALTER TABLE [dbo].[jnd_appointment_doctor] CHECK CONSTRAINT [jndppntmnppntmntdctrfk]
GO
ALTER TABLE [dbo].[jnd_appointment_doctor]  WITH CHECK ADD  CONSTRAINT [jndppntmntdctppntmntfk] FOREIGN KEY([appointment_fk])
REFERENCES [dbo].[APPOINTMENT] ([ID])
GO
ALTER TABLE [dbo].[jnd_appointment_doctor] CHECK CONSTRAINT [jndppntmntdctppntmntfk]
GO
ALTER TABLE [dbo].[jnd_appointment_patient]  WITH CHECK ADD  CONSTRAINT [jndppntmnppntmntptntfk] FOREIGN KEY([appointment_patient_fk])
REFERENCES [dbo].[PERSON] ([ID])
GO
ALTER TABLE [dbo].[jnd_appointment_patient] CHECK CONSTRAINT [jndppntmnppntmntptntfk]
GO
ALTER TABLE [dbo].[jnd_appointment_patient]  WITH CHECK ADD  CONSTRAINT [jndppntmntptnppntmntfk] FOREIGN KEY([appointment_fk])
REFERENCES [dbo].[APPOINTMENT] ([ID])
GO
ALTER TABLE [dbo].[jnd_appointment_patient] CHECK CONSTRAINT [jndppntmntptnppntmntfk]
GO
ALTER TABLE [dbo].[jnd_prescription_doctor]  WITH CHECK ADD  CONSTRAINT [jndprscrprscrptndctrfk] FOREIGN KEY([prescription_doctor_fk])
REFERENCES [dbo].[PERSON] ([ID])
GO
ALTER TABLE [dbo].[jnd_prescription_doctor] CHECK CONSTRAINT [jndprscrprscrptndctrfk]
GO
ALTER TABLE [dbo].[jnd_prescription_doctor]  WITH CHECK ADD  CONSTRAINT [jndprscrptndprscrptnfk] FOREIGN KEY([prescription_fk])
REFERENCES [dbo].[PRESCRIPTION] ([ID])
GO
ALTER TABLE [dbo].[jnd_prescription_doctor] CHECK CONSTRAINT [jndprscrptndprscrptnfk]
GO
ALTER TABLE [dbo].[jnd_prescription_patient]  WITH CHECK ADD  CONSTRAINT [jndprscrprscrptnptntfk] FOREIGN KEY([prescription_patient_fk])
REFERENCES [dbo].[PERSON] ([ID])
GO
ALTER TABLE [dbo].[jnd_prescription_patient] CHECK CONSTRAINT [jndprscrprscrptnptntfk]
GO
ALTER TABLE [dbo].[jnd_prescription_patient]  WITH CHECK ADD  CONSTRAINT [jndprscrptnpprscrptnfk] FOREIGN KEY([prescription_fk])
REFERENCES [dbo].[PRESCRIPTION] ([ID])
GO
ALTER TABLE [dbo].[jnd_prescription_patient] CHECK CONSTRAINT [jndprscrptnpprscrptnfk]
GO
USE [master]
GO
ALTER DATABASE [DentalCare] SET  READ_WRITE 
GO
