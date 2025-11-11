document.addEventListener('DOMContentLoaded', () => {
    const regionOverlays = document.querySelectorAll('.region-overlay');
    const popups = document.querySelectorAll('.popup');
    let activePopup = null; // เก็บ pop-up ที่กำลังแสดง

    regionOverlays.forEach(overlay => {
        overlay.addEventListener('mouseenter', (event) => {
            // ซ่อน pop-up ที่เคยแสดงอยู่ (ถ้ามี)
            if (activePopup) {
                activePopup.style.opacity = '0';
                activePopup.style.visibility = 'hidden';
                activePopup.style.transform = 'translateY(10px)';
            }

            // แสดง pop-up ที่เกี่ยวข้อง
            const region = overlay.dataset.region;
            const popupToShow = document.getElementById(`popup-${region}`);
            if (popupToShow) {
                popupToShow.style.opacity = '1';
                popupToShow.style.visibility = 'visible';
                popupToShow.style.transform = 'translateY(0)';
                activePopup = popupToShow; // กำหนดให้ pop-up นี้เป็น active
            }
        });

        // เมื่อเมาส์ออกจากพื้นที่ภาค ให้ซ่อน Pop-up
        overlay.addEventListener('mouseleave', () => {
            if (activePopup) {
                activePopup.style.opacity = '0';
                activePopup.style.visibility = 'hidden';
                activePopup.style.transform = 'translateY(10px)';
                activePopup = null; // ไม่มี pop-up active แล้ว
            }
        });
    });

    // (ทางเลือก) ซ่อน pop-up เมื่อเมาส์ออกจาก map-container ทั้งหมด
    const mapContainer = document.querySelector('.map-container');
    mapContainer.addEventListener('mouseleave', () => {
        if (activePopup) {
            activePopup.style.opacity = '0';
            activePopup.style.visibility = 'hidden';
            activePopup.style.transform = 'translateY(10px)';
            activePopup = null;
        }
    });

    // (ทางเลือก) ซ่อน pop-up เมื่อคลิกที่อื่นบนหน้าจอ
    document.addEventListener('click', (event) => {
        if (activePopup && !event.target.closest('.map-container') && !event.target.closest('.popup')) {
            activePopup.style.opacity = '0';
            activePopup.style.visibility = 'hidden';
            activePopup.style.transform = 'translateY(10px)';
            activePopup = null;
        }
    });
});
