import React from 'react'
import NavBar1 from '../Components/NavBar1'
import axios from 'axios';
import { toast } from 'react-toastify';

function ShipmentQuotePage() {
  
  

  const handleSubmit = async (e: React.FormEvent) => {
    
    e.preventDefault();

    console.log(e)

    // personal info 
    let firstName : string = (e.target as any).form[0].value
    let lastName : string = (e.target as any).form[1].value
    let email : string = (e.target as any).form[2].value
    let phonenumber : string = (e.target as any).form[3].value
    let movingDate : Date = (e.target as any).form[4].value
    //Radio Btns
    let emailValueRadioBtn :boolean = (e.target as any).form[5].checked
    let PhoneNumberRadioBtn :boolean = (e.target as any).form[6].checked
    let BothValueRadioBtn :boolean = (e.target as any).form[7].checked
    let additionalComments : string = (e.target as any).form[8].value

    let wayToContact : string = "BOTH"
    if(emailValueRadioBtn){
      wayToContact = "EMAIL"
    } else if (PhoneNumberRadioBtn) {
      wayToContact = "PHONE_NUMBER"
    } else if (BothValueRadioBtn){
      wayToContact = "BOTH"
    }

    

    //pick up location info
    let pickUpAddress : string = (e.target as any).form[9].value
    let cityP : string = (e.target as any).form[10].value
    let postalCodeP : string = (e.target as any).form[11].value
    let countryP : string = (e.target as any).form[12].value
    let buildingTypeP :string = (e.target as any).form[13].value
    let numberofRoomP : number = (e.target as any).form[14].value
    // elevator Pick up
    let elevatorYesRadioBtnP :boolean = (e.target as any).form[15].checked
    let elevatorNoRadioBtnP :boolean = (e.target as any).form[16].checked

    let elevatorisPresentP : boolean
    if(elevatorYesRadioBtnP){
      elevatorisPresentP = true
    } else {
      elevatorisPresentP = false
    } 

    // drop off destination
    let dropOffAddress : string = (e.target as any).form[17].value
    let cityD : string = (e.target as any).form[18].value
    let postalCodeD : string = (e.target as any).form[19].value
    let countryD : string = (e.target as any).form[20].value
    let buildingTypeD :string = (e.target as any).form[21].value
    let numberofRoomD : number = (e.target as any).form[22].value
    // elevator Drop off
    let elevatorYesRadioBtnD :boolean = (e.target as any).form[23].checked
    let elevatorNoRadioBtnD :boolean = (e.target as any).form[24].checked
    
    let elevatorisPresentD : boolean
    if(elevatorYesRadioBtnD){
      elevatorisPresentD = true
    } else {
      elevatorisPresentD = false
    } 

    // shipment name
    let shipmentName : string = (e.target as any).form[25].value

    const quoteForm = {
      "pickupStreetAddress": pickUpAddress,
      "pickupCity": cityP,
      "pickupProvince": "null",
      "pickupCountry": countryP,
      "pickupPostalCode": postalCodeP,
      "pickupRoomNumber": numberofRoomP,
      "pickupElevator": elevatorisPresentP,
      "pickupBuildingType": buildingTypeP,
      "destinationStreetAddress": dropOffAddress,
      "destinationCity": cityD,
      "destinationProvince": "null",
      "destinationCountry": countryD,
      "destinationPostalCode": postalCodeD,
      "destinationRoomNumber": numberofRoomD,
      "destinationElevator": elevatorisPresentD,
      "destinationBuildingType": buildingTypeD,
      "firstName": firstName,
      "lastName": lastName,
      "emailAddress": email,
      "phoneNumber": phonenumber,
      "contactMethod": wayToContact,
      "expectedMovingDate": movingDate,
      "comment": additionalComments
    }

    console.log(quoteForm)

    try {
      // Make a POST request using Axios
      const response = await axios.post(
        'http://localhost:8080/api/v1/movingexpress/quotes/request',
        quoteForm,
        {
          headers: {
            'Content-Type': 'application/json',
          },
        }
      );
      console.log('Response:', response.data);
    
      toast.success('Request quote sent successfully !', {
        position: 'top-right',
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: 'light',
      });
    } catch (error) {
      console.error('Error:', error);
      toast.error('Error something happened', {
        position: 'top-right',
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: 'light',
      });
    }
  }



  return (
    <div>
      <NavBar1></NavBar1>

      <div className='px-[5%] py-20'>

        <div className='flex flex-col gap-3 pb-7'>
          
          <div style={{fontFamily : "Bebas Neue, cursive"}} className='text-3xl'>Shipment <span className="text-companyYellow">Quote</span></div>
          
          <div className="pb-5 font-light text-sm opacity-90 lg:pr-[20%]">
            A shipment quote plays a vital role in ensuring the smooth and organized movement of goods 
            from the point of origin to the destination. It helps both the shipper and the carrier have 
            a clear understanding of the logistics involved, reducing the risk of errors, delays, and 
            misunderstandings during the transportation process.
          </div>
          
          <hr className="border-1 border-companyYellow" />
        </div>

        
        <div>
          <form>

            <div className='flex flex-col gap-2 mb-10'>
              
              <div style={{fontFamily : "Bebas Neue, cursive"}} className='text-xl'>Personal <span className="text-companyYellow">information</span></div>
              
              <div className="pb-5 font-light text-sm opacity-90">
                Please provide and fill in all your personal information in the section below.
              </div>
              
              <div className='bg-white shadow-lg border rounded-md flex flex-col gap-5 px-10 py-10'> 
                  
                  <div className='flex flex-row flex-wrap gap-5'>

                    <div className='flex flex-col gap-1'>
                      <input type="text" className='border border-[lightgray] text-sm h-[35px] px-4 rounded-sm' id='FirstNameInput' name='FirstNameInput' placeholder='First Name'/>
                      <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>First Name</label>
                    </div>

                    <div className='flex flex-col gap-1'>
                      <input type="text" className='border border-[lightgray] text-sm h-[35px] px-4 rounded-sm' id='LastNameInput' name='LastNameInput' placeholder='Last Name'/>
                      <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>Last Name</label>
                    </div>

                    <div className='flex flex-col gap-1'>
                      <input type="email" className='border border-[lightgray] text-sm h-[35px] px-4 rounded-sm' id='EmailInput' name='EmailInput' placeholder='E-Mail'/>
                      <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>E-Mail</label>
                    </div>

                    <div className='flex flex-col gap-1'>
                      <input type="text" className='border border-[lightgray] text-sm  h-[35px] px-4 rounded-sm py-1' name='PhoneNumberInput' id='PhoneNumberInput' placeholder='Phone Number'/>
                      <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>Phone Number</label>
                    </div>

                  </div>
                  
                  <div className='flex gap-5 flex-wrap'>

                    <div className='flex flex-col gap-1'>
                      <input type="date" className='border border-[lightgray] w-[200px] rounded-sm px-4 py-1' name='MovingDateInput' id='MovingDateInput'/>
                      <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>Expected Moving Date</label>
                    </div>

                    <div className='text-[#696969] text-sm'>
                      <div>How to contact you ?</div>
                      
                      <div className='flex gap-5'>
                        
                        <label className='flex gap-1'>
                          <input type="radio" name="contact" id='EmailValue' value="email"/>
                          <span>E-Mail</span>
                        </label>
                        
                        <label className='flex gap-1'>
                          <input type="radio" name="contact" id='PhoneNumberValue' value="phonenumber"/>
                          <span>Phone Number</span>
                        </label>
                        
                        <label className='flex gap-1'>
                          <input type="radio" name="contact" id='BothValue' value="both"/>
                          <span>Both</span>
                        </label>

                      </div>

                    </div>

                  </div>
                  
                  <div>
                    <textarea className="w-[100%] border-[lightgray] h-[100px] border text-sm px-3 py-3 rounded-sm" placeholder='Additional comments / instrcutions (Specific hours, crane required...)' id='AdditionalCommentsInput' name='AdditionalCommentsInput'/>
                  </div>

              </div>

            </div>

            <div className='flex flex-col gap-2 mb-10'>
              
              <div style={{fontFamily : "Bebas Neue, cursive"}} className='text-xl'>Pick Up <span className="text-companyYellow">Location</span></div>
              
              <div className="pb-5 font-light text-sm opacity-90">
                Please provide and fill in all the information about the pick up location in the section below.
              </div>
              
              <div className='bg-white shadow-lg border rounded-md flex flex-col gap-5 px-10 py-10'> 
                  
                <div className='flex flex-col gap-1'>
                  <input type="text" className='border border-[lightgray] text-sm  h-[35px] px-4 rounded-sm py-1 w-[85%]' placeholder='Pick Up Address' id='PickUpAddressInput' name='PickUpAddressInput'/>
                  <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>Pick up address</label>
                </div>

                <div className="flex gap-5 flex-wrap">
                  <div className='flex flex-col gap-1'>
                    <input type="text" className='border border-[lightgray] text-sm  h-[35px] px-4 rounded-sm py-1 w-[300px]' id='CityInputP' name='CityInputP' placeholder='City'/>
                    <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>city</label>
                  </div>

                  <div className='flex flex-col gap-1'>
                    <input type="text" className='border border-[lightgray] text-sm  h-[35px] px-4 rounded-sm py-1 w-[300px]' id='PostalCodeInputP' name='PostalCodeInputP' placeholder='Postal Code'/>
                    <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>Postal Code</label>
                  </div>

                  <div className='flex flex-col gap-1'>
                    <select id='CountrySelectInputP'  className='border border-[lightgray] py-1 px-1'>
                      <optgroup>
                        <option id='CanadaOptionP' value="CA">CA</option>
                        <option id='USAOptionP'  value="USA">USA</option>
                      </optgroup>
                    </select>
                    <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>Country</label>
                  </div>
                </div>

                <div className="flex gap-5 flex-wrap">
                  <div className='flex flex-col gap-1'>
                    <select id='BuildingTypeSelectInputP' className='border border-[lightgray] py-1 px-1 w-[200px]'>
                      <optgroup>
                        <option id='nullP' value="null"></option>
                        <option id='HouseP' value="house">House</option>
                        <option id='VillaP' value="villa">Villa</option>
                        <option id='CondoP' value="condo">Condo</option>
                        <option id='ApartmentP' value="Apartment">Apartment</option>
                      </optgroup>
                    </select>
                    <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>building type</label>
                  </div>

                  <div className='flex flex-col gap-1'>
                    <input type="number" className='border border-[lightgray] text-sm  h-[35px] px-4 rounded-sm py-1' placeholder='0' id='NumberOfRoomsInputP' name='NumberOfRoomsInputP'/>
                    <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>No. of rooms</label>
                  </div>

                  <div className='text-[#696969] text-sm'>
                      <div>Is there an elevator ?</div>
                      
                      <div className='flex gap-5'>
                        
                        <label className='flex gap-1'>
                          <input type="radio" name="elevatorP" id='YesOptionP' value="yes"/>
                          <span>Yes</span>
                        </label>
                        
                        <label className='flex gap-1'>
                          <input type="radio" name="elevatorP" id='NoOptionP' value="no"/>
                          <span>No</span>
                        </label>
                    
                      </div>

                    </div>

                  
                </div>

              </div>

              
            </div>

            <div className='flex flex-col gap-2 mb-10'>
              
              <div style={{fontFamily : "Bebas Neue, cursive"}} className='text-xl'>Drop Off <span className="text-companyYellow">Destination</span></div>
              
              <div className="pb-5 font-light text-sm opacity-90">
                Please provide and fill in all the information about the drop off destination in the section below.
              </div>
              
              <div className='bg-white shadow-lg border rounded-md flex flex-col gap-5 px-10 py-10'> 
                  
                <div className='flex flex-col gap-1'>
                  <input type="text" className='border border-[lightgray] text-sm  h-[35px] px-4 rounded-sm py-1 w-[85%]' placeholder='Drop Off Address' name='DropOffAddressInput' id='DropOffAddressInput'/>
                  <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>Drop Off address</label>
                </div>

                <div className="flex gap-5 flex-wrap">
                  <div className='flex flex-col gap-1'>
                    <input type="text" className='border border-[lightgray] text-sm  h-[35px] px-4 rounded-sm py-1 w-[300px]' placeholder='City' name='CityInputD' id='CityInputD'/>
                    <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>city</label>
                  </div>

                  <div className='flex flex-col gap-1'>
                    <input type="text" className='border border-[lightgray] text-sm  h-[35px] px-4 rounded-sm py-1 w-[300px]' placeholder='Postal Code' id='PostalCodeInputD' name='PostalCodeInputD'/>
                    <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>Postal Code</label>
                  </div>

                  <div className='flex flex-col gap-1'>
                    <select id='CountrySelectInputD' className='border border-[lightgray] py-1 px-1'>
                      <optgroup>
                        <option id='CanadaOptionD' value="CA">CA</option>
                        <option id='USAOptionD' value="USA">USA</option>
                      </optgroup>
                    </select>
                    <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>Country</label>
                  </div>
                </div>

                <div className="flex gap-5 flex-wrap">
                  <div className='flex flex-col gap-1'>
                    <select id='BuildingTypeSelectInputD' className='border border-[lightgray] py-1 px-1 w-[200px]'>
                      <optgroup>
                        <option id='nullD' value="null"></option>
                        <option id='HouseD' value="house">House</option>
                        <option id='VillaD' value="villa">Villa</option>
                        <option id='CondoD' value="condo">Condo</option>
                        <option id='ApartmentD' value="Apartment">Apartment</option>
                      </optgroup>
                    </select>
                    <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>building type</label>
                  </div>

                  <div className='flex flex-col gap-1'>
                    <input type="number" className='border border-[lightgray] text-sm  h-[35px] px-4 rounded-sm py-1' placeholder='0' name='NumberOfRoomsInputD' id='NumberOfRoomsInputD'/>
                    <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>No. of rooms</label>
                  </div>

                  <div className='text-[#696969] text-sm'>
                    <div>Is there an elevator ?</div>
                    
                    <div className='flex gap-5'>
                      
                      <label className='flex gap-1'>
                        <input type="radio" name="elevatorD" id='YesOptionD' value="yes"/>
                        <span>Yes</span>
                      </label>
                      
                      <label className='flex gap-1'>
                        <input type="radio" name="elevatorD" id='NoOptionD' value="no"/>
                        <span>No</span>
                      </label>
                  
                    </div>

                  </div>

                  
                </div>
              </div>

            </div>


            <div>
              <div className="flex gap-5">

                <div className='flex flex-col gap-1 w-[60%] sm:w-[40%] md:w-[30%]'>
                  <input style={{height: "35px"}} type="text" className='border px-6 text-sm rounded-sm' placeholder='Enter Shipment Name' name='ShipmentNameInput' id='ShipmentNameInput' />
                  <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969]'>Shipment Name</label>
                </div>

                <div>
                  <button type="submit" onClick={handleSubmit} className="text-white text-[13px] bg-companyYellow px-10 py-2 rounded-md shadow-md" style={{fontFamily : "Bebas Neue, cursive"}} name='RequestQuoteBtn' id='RequestQuoteBtn'>Request Quote</button>
                </div>

              </div>
            </div>

          </form>
        </div>

      </div>

    </div>
  )
}

export default ShipmentQuotePage