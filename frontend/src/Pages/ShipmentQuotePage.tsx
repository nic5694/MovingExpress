import React from 'react'
import NavBar1 from '../Components/NavBar1'

function ShipmentQuotePage() {
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
                      <input type="text" className='border border-[lightgray] text-sm h-[35px] px-4 rounded-sm' placeholder='First Name'/>
                      <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>First Name</label>
                    </div>

                    <div className='flex flex-col gap-1'>
                      <input type="text" className='border border-[lightgray] text-sm h-[35px] px-4 rounded-sm' placeholder='Last Name'/>
                      <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>Last Name</label>
                    </div>

                    <div className='flex flex-col gap-1'>
                      <input type="email" className='border border-[lightgray] text-sm h-[35px] px-4 rounded-sm' placeholder='E-Mail'/>
                      <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>E-Mail</label>
                    </div>

                    <div className='flex flex-col gap-1'>
                      <input type="text" className='border border-[lightgray] text-sm  h-[35px] px-4 rounded-sm py-1' placeholder='Phone number'/>
                      <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>Phone Number</label>
                    </div>

                  </div>
                  
                  <div className='flex gap-5 flex-wrap'>

                    <div className='flex flex-col gap-1'>
                      <input type="date" className='border border-[lightgray] w-[200px] rounded-sm px-4 py-1'/>
                      <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969] text-sm'>Expected Moving Date</label>
                    </div>

                    <div className='text-[#696969] text-sm'>
                      <div>How to contact you ?</div>
                      
                      <div className='flex gap-5'>
                        
                        <label className='flex gap-1'>
                          <input type="radio" name="contact" value="email"/>
                          <span>E-Mail</span>
                        </label>
                        
                        <label className='flex gap-1'>
                          <input type="radio" name="contact" value="email"/>
                          <span>Phone Number</span>
                        </label>
                        
                        <label className='flex gap-1'>
                          <input type="radio" name="contact" value="email"/>
                          <span>Both</span>
                        </label>

                      </div>

                    </div>

                  </div>
                  
                  <div>
                    <textarea className="w-[100%] border-[lightgray] h-[100px] border text-sm px-3 py-3 rounded-sm" placeholder='Additional comments / instrcutions (Specific hours, crane required...)'/>
                  </div>

              </div>

            </div>

            <div className='flex flex-col gap-2 mb-10'>
              
              <div style={{fontFamily : "Bebas Neue, cursive"}} className='text-xl'>Pick Up <span className="text-companyYellow">Location</span></div>
              
              <div className="pb-5 font-light text-sm opacity-90">
                Please provide and fill in all the information about the pick up location in the section below.
              </div>
              
              <div className='bg-white h-[300px] shadow-lg border rounded-md'> 
                
              </div>
              
            </div>

            <div className='flex flex-col gap-2 mb-10'>
              
              <div style={{fontFamily : "Bebas Neue, cursive"}} className='text-xl'>Drop Off <span className="text-companyYellow">Destination</span></div>
              
              <div className="pb-5 font-light text-sm opacity-90">
                Please provide and fill in all the information about the drop off destination in the section below.
              </div>
              
              <div className='bg-white h-[300px] shadow-lg border rounded-md'> 

              </div>
              
            </div>


            <div>
              <div className="flex gap-5">

                <div className='flex flex-col gap-1 w-[60%] sm:w-[40%] md:w-[30%]'>
                  <input style={{height: "35px"}} type="text" className='border px-6 text-sm rounded-sm' placeholder='Enter Shipment Name' />
                  <label style={{fontFamily : "Bebas Neue, cursive"}} className='text-[#696969]'>Shipment Name</label>
                </div>

                <div>
                  <button className="text-white text-[13px] bg-companyYellow px-10 py-2 rounded-md shadow-md" style={{fontFamily : "Bebas Neue, cursive"}}>Request Quote</button>
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