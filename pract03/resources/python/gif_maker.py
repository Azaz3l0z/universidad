import os
import sys
import imageio

def main(download_path: str, FPS: int):
    FPS = int(FPS)

    if getattr(sys, 'frozen', False):
        application_path = os.path.dirname(os.path.abspath(sys.executable))
    elif __file__:
        application_path = os.path.dirname(os.path.abspath(__file__))

    files = []
    img_dir = os.path.join(os.path.dirname(application_path), 'images')
    for dirpath,_,filenames in os.walk(img_dir):
        for f in filenames:
            files.append(os.path.abspath(os.path.join(dirpath, f)))
    files.sort(key=lambda x: int(float(os.path.basename(x).replace('.png', ''))*100))

    with imageio.get_writer(os.path.join(download_path, 'circulos.gif'), mode='I', duration = 1/FPS) as writer:
        for filename in files:
            image = imageio.imread(filename)
            writer.append_data(image)
            
if __name__ == "__main__":
    main(*sys.argv[1:])