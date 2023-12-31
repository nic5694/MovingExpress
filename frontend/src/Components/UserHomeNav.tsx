import React, { useState } from 'react'
import logo from '../Images/ME_Logo.png'
import { Link } from 'react-router-dom'

function UserHomeNav(props:any) {

  const[menuOpen,setMenuOpen] = useState(false)

  const menuIcon = () => {
    return(
      <svg className='pt-1' width="25" viewBox="0 0 89 75" fill="none" xmlns="http://www.w3.org/2000/svg">
        <rect width="89" height="13" fill="white"/>
        <rect y="31" width="89" height="13" fill="white"/>
        <rect y="62" width="89" height="13" fill="white"/>
      </svg>
    )
  }



  return (
    <div>

      {/* phone version nav bar */}
      <div style={menuOpen? {alignItems : "center", backgroundColor : "#2D2D2D"} : {alignItems : "center"}} className="px-5 py-4 flex justify-between lg:hidden ">

        <div>
            <img src={logo} alt="logo" className="w-24 2xl:w-32" />
        </div>

        <div>
          <button onClick={() => {setMenuOpen(!menuOpen)}}>
             {menuIcon()}
          </button>
        </div>

      </div>

      <div className='absolute lg:hidden text-[gray]' style={menuOpen ? {width: "100%" ,height: "100%", overflow: "hidden"} : {height: "0", overflow: "hidden"}}>
        <div className='bg-white shadow-2xl'>
          <Link to={"/Home"}>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='px-5 py-2 border-b bg-white hover:bg-companyYellow hover:text-[#2D2D2D] hover:pl-10 duration-300 ease-in-out '>Home</div>
          </Link>
          <Link to={"/UserShipmentQuote"}>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='px-5 py-2 border-b bg-white hover:bg-companyYellow hover:text-[#2D2D2D] hover:pl-10 duration-300 ease-in-out '>Shipment Quote</div>
          </Link>
          <Link to={"/Shipments"}>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='px-5 py-2 border-b bg-white hover:bg-companyYellow hover:text-[#2D2D2D] hover:pl-10 duration-300 ease-in-out '>Shipments</div>
          </Link>
          <Link to={"/ContactUs"}>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='px-5 py-2 border-b bg-white hover:bg-companyYellow hover:text-[#2D2D2D] hover:pl-10 duration-300 ease-in-out '>Contact Us</div>
          </Link>
          <Link to={"/Profile"}>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='px-5 py-2 border-b bg-white hover:bg-companyYellow hover:text-[#2D2D2D] hover:pl-10 duration-300 ease-in-out '>Profile</div>
          </Link>
          <Link to={"/"}>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='px-5 py-2 border-b bg-white hover:bg-companyYellow hover:text-[#2D2D2D] hover:pl-10 duration-300 ease-in-out '>Logout</div>
          </Link>
        </div>
      </div>

      {/* normal version nav bar */}
      <div style={{alignItems: "center"}} className='hidden lg:block px-5 py-4 lg:flex justify-between text-white'>
          <div><img src={logo} alt="logo" className="w-24 2xl:w-32" /></div>

          <div style={{letterSpacing: "1px"}} className='flex flex-row gap-10 text-sm'>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }} ><Link className='hover:underline underline-offset-4 decoration-2 decoration-companyYellow' to={"/Home"}>Home</Link></div>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }}><Link className='hover:underline underline-offset-4 decoration-2 decoration-companyYellow' to={"/UserShipmentQuote"}>Shipment Quote</Link></div>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }}><Link className='hover:underline underline-offset-4 decoration-2 decoration-companyYellow' to={"/Shipments"}>Shipments</Link></div>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }}><Link className='hover:underline underline-offset-4 decoration-2 decoration-companyYellow' to={"/ContactUs"}>Contact Us</Link></div>
          </div>

          <div style={{alignItems: "center"}} className='flex flex-row gap-10 text-sm'>
             <div style={{ fontFamily: 'Bebas Neue, cursive', letterSpacing: "1px" }}>
                <Link to={"/Profile"}>[ Full Name ]</Link>
              </div>

            <div>
              <button style={{ fontFamily: 'Bebas Neue, cursive' }} className='border-[2px] py-1 px-5 rounded-sm border-companyYellow text-white'>Log Out</button>
            </div>
          </div>

         
      </div>


    </div>
  )
}

export default UserHomeNav