import React from 'react'
import logo from '../Images/ME_Logo_Blck.png'

function Footer() {
    return (
        <div className="bg-companyYellow gap-20 flex pl-[5%] py-[75px]">
            <div className="hidden md:block">
                <img src={logo} className="w-52" />
            </div>

            <div className="flex gap-10 mt-5 ">
                <div className="flex flex-col text-white">
                    <div
                        style={{ fontFamily: 'Bebas Neue, cursive' }}
                        className="text-lg"
                    >
                        Account
                    </div>
                    <a href="">Sign Up</a>
                    <a href="">Login</a>
                </div>
                <div className="flex flex-col text-white">
                    <div
                        style={{ fontFamily: 'Bebas Neue, cursive' }}
                        className="text-lg"
                    >
                        Support
                    </div>
                    <a href="">link 1</a>
                </div>

                <div className="flex flex-col text-white">
                    <div
                        style={{ fontFamily: 'Bebas Neue, cursive' }}
                        className="text-lg"
                    >
                        Explore
                    </div>
                    <a href="">link 1</a>
                    <a href="">link 2</a>
                    <a href="">link 3</a>
                </div>

                <div className="flex flex-col text-white">
                    <div
                        style={{ fontFamily: 'Bebas Neue, cursive' }}
                        className="text-lg"
                    >
                        Location
                    </div>
                    <a href="">link 1</a>
                    <a href="">link 2</a>
                </div>
            </div>
        </div>
    )
}

export default Footer
