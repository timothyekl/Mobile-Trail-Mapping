#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>
#import "TrailPoint.h"
#import "LocationMarker.h"

@interface MapView : UIView<MKMapViewDelegate> {
	MKMapView* mapView;
}

@end
